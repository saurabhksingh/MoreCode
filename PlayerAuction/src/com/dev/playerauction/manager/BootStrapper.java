package com.dev.playerauction.manager;

import com.dev.playerauction.constants.Constants;
import com.dev.playerauction.constants.StrengthTypes;
import com.dev.playerauction.constants.XMLAttributes;
import com.dev.playerauction.exception.ErrorCode;
import com.dev.playerauction.exception.PlayerAuctionException;
import com.dev.playerauction.model.Player;
import com.dev.playerauction.model.PlayersCollection;
import com.dev.playerauction.util.ArithmeticUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class loads XML data in a runtime data structure.
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class BootStrapper {

    /**
     * Here we extract out all information related to a player and calculate/validate the player data and then store it
     * in a runtime data structure wrapping over a min-heap.
     * @param xmlDataName  Name of xml file containing the data of all players
     * @param buyersCount  Number of buyers for buying the players
     * @return Wrapper class over min-heap data runtime data structure.
     * @throws PlayerAuctionException
     */
    public static PlayersCollection loadXmlInPlayersCollection(String xmlDataName, int buyersCount) throws PlayerAuctionException {

        PlayersCollection playersCollection = new PlayersCollection();

        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(PlayersAuctionManager.class.getClassLoader().getResourceAsStream(xmlDataName));
            NodeList nodeList = document.getElementsByTagName(XMLAttributes.PLAYER_DATA_TAG);

            for(int i=0; i<nodeList.getLength(); i++){

                Element element = (Element)nodeList.item(i);
                String name = element.getElementsByTagName(XMLAttributes.PLAYER_NAME_TAG).item(0).getTextContent();
                int jerseyNumber = Integer.valueOf(element.getElementsByTagName(XMLAttributes.PLAYER_STRENGTH_JERSEY_TAG).item(0).getTextContent());

                int battingStrength = Integer.valueOf(element.getElementsByTagName(XMLAttributes.PLAYER_BATTING_STRENGTH_TAG).item(0).getTextContent());
                validateStrength(StrengthTypes.BATTING_STRENGTH, battingStrength);
                int bowlingStrength = Integer.valueOf(element.getElementsByTagName(XMLAttributes.PLAYER_BOWLING_STRENGTH_TAG).item(0).getTextContent());
                validateStrength(StrengthTypes.BOWLING_STRENGTH, bowlingStrength);
                int fieldingStrength = Integer.valueOf(element.getElementsByTagName(XMLAttributes.PLAYER_FIELDING_STRENGTH_TAG).item(0).getTextContent());
                validateStrength(StrengthTypes.FIELDING_STRENGTH, fieldingStrength);

                int keepingStrength = Integer.valueOf(element.getElementsByTagName(XMLAttributes.PLAYER_STRENGTH_KEEP_PLAYING_TAG).item(0).getTextContent());

                validateStrength(StrengthTypes.KEEPING_STRENGTH, keepingStrength);

                double price = ArithmeticUtil.getPlayerPrice(battingStrength, bowlingStrength, fieldingStrength, keepingStrength);

                Player player = new Player(name, price, (battingStrength+bowlingStrength+fieldingStrength+keepingStrength), jerseyNumber);
                buyersCount = buyersCount<=0 ? Integer.MAX_VALUE : buyersCount;
                player.setMaxRejectCount(buyersCount);
                playersCollection.addPlayer(player);
            }

        }
        catch (ParserConfigurationException pce){
            throw new PlayerAuctionException(ErrorCode.INVALID_DATA_XML, pce.getMessage());
        } catch (SAXException se) {
            throw new PlayerAuctionException(ErrorCode.INVALID_DATA_XML, se.getMessage());
        } catch (IOException ioe) {
            throw new PlayerAuctionException(ErrorCode.INVALID_DATA_XML, ioe.getMessage());
        }

        return playersCollection;
    }

    /**
     * Validate the strength data of players
     * @param strengthType type of strength (batting, bowling, fielding etc) being validated
     * @param strength value of the strength given in xml
     * @throws PlayerAuctionException
     */
    private static void validateStrength(StrengthTypes strengthType, int strength) throws PlayerAuctionException{

        switch (strengthType){

            case BATTING_STRENGTH:
                if(strength < Constants.MIN_BATTING_STRENGTH || strength > Constants.MAX_BATTING_STRENGTH){
                    throw new PlayerAuctionException(ErrorCode.INVALID_BATTING_STRENGTH, "Batting strength is not in range of "
                            +Constants.MIN_BATTING_STRENGTH + " and " + Constants.MAX_BATTING_STRENGTH);
                }
                break;

            case BOWLING_STRENGTH:
                if(strength < Constants.MIN_BOWLING_STRENGTH || strength > Constants.MAX_BOWLING_STRENGTH){
                    throw new PlayerAuctionException(ErrorCode.INVALID_BOWLING_STRENGTH, "Bowling strength is not in range of "
                            +Constants.MIN_BOWLING_STRENGTH + " and " + Constants.MAX_BOWLING_STRENGTH);
                }
                break;

            case FIELDING_STRENGTH:
                if(strength < Constants.MIN_FIELDING_STRENGTH || strength > Constants.MAX_FIELDING_STRENGTH){
                    throw new PlayerAuctionException(ErrorCode.INVALID_FIELDING_STRENGTH, "Fielding strength is not in range of "
                            +Constants.MIN_FIELDING_STRENGTH + " and " + Constants.MAX_FIELDING_STRENGTH);
                }
                break;

            case KEEPING_STRENGTH:
                if(Arrays.binarySearch(Constants.VALID_KEEPING_STRENGTH_VALUES, strength) < 0){
                    throw new PlayerAuctionException(ErrorCode.INVALID_KEEPING_STRENGTH, "Keeping strength is not valid, it shpuld be one of "
                            +Constants.VALID_KEEPING_STRENGTH_VALUES);
                }
                break;
        }
    }
}
