package com.dev.saurabh;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/13/12
 * Time: 12:54 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class TiffToPdf {
    /**
     * There are already lot of sample java codes which show how you can create PDF documents from image of various formats like jpeg, jpeg2000,gif, tif, png etc.

     So I am not going to make that effort again

     Here the different thing I am going to show is how to create a PDF document from a multi-page TIF image maintaining the original resolution of the image.
     */

    /**
     * @param args
     * @throws DocumentException
     * @throws IOException
     */
    public static void main(String[] args) throws DocumentException,
            IOException {

        String imgeFilename = "/home/saurabh/Downloads/image.tif";

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(
                document,
                new FileOutputStream("/home/saurabh/Desktop/out"
                        + Math.random() + ".pdf"));
        writer.setStrictImageSequence(true);
        document.open();

        document.add(new Paragraph("Multipages tiff file"));
        Image image;
        RandomAccessFileOrArray ra = new RandomAccessFileOrArray(imgeFilename);
        int pages = TiffImage.getNumberOfPages(ra);
        for (int i = 1; i <= pages; i++) {
            image = TiffImage.getTiffImage(ra, i);
            Rectangle pageSize = new Rectangle(image.getWidth(),
                    image.getHeight());
            document.setPageSize(pageSize);
            document.add(image);
            document.newPage();
        }

        document.close();

    }
}
