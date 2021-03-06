/*
 * Created at 06.01.2004 14:39:17
 * Copyright (c) 2004 by Norman Fomferra
 */
package z.core;

import org.jdom.Element;
import org.jdom.JDOMException;
import z.core.color.RGBA;
import z.util.JDOMExternalizable;
import z.util.JDOMHelper;

public class ImageInfo implements JDOMExternalizable {
    public static final String ELEMENT_NAME = "imageInfo"; // NON-NLS

    public static final RGBA DEFAULT_BACKGROUND = RGBA.BLACK; // RGBA.TRANSPARENT;

    public static final String DEFAULT_PATH = "z-out.png"; // NON-NLS

    public static final String DEFAULT_FORMAT = "PNG";  // NON-NLS

    public static final int DEFAULT_SIZE = 512;

    private String imagePath;

    private String imageFormat;

    private int imageWidth;

    private int imageHeight;

    private boolean usingWindowSize;

    private RGBA background;

    public ImageInfo() {
        initComponent();
    }

    public void initComponent() {
        setImagePath(DEFAULT_PATH);
        setImageFormat(DEFAULT_FORMAT);
        setImageWidth(DEFAULT_SIZE);
        setImageHeight(DEFAULT_SIZE);
        setUsingWindowSize(false);
        setBackground(DEFAULT_BACKGROUND);
    }

    public void disposeComponent() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public RGBA getBackground() {
        return background;
    }

    public void setBackground(RGBA background) {
        this.background = background;
    }

    public boolean isUsingWindowSize() {
        return usingWindowSize;
    }

    public void setUsingWindowSize(boolean usingWindowSize) {
        this.usingWindowSize = usingWindowSize;
    }

    public void writeExternal(Element element) throws JDOMException {
        JDOMHelper.setAttributeString(element, "path", getImagePath()); // NON-NLS
        JDOMHelper.setAttributeString(element, "format", getImageFormat()); // NON-NLS
        JDOMHelper.setAttributeInt(element, "width", getImageWidth()); // NON-NLS
        JDOMHelper.setAttributeInt(element, "height", getImageHeight()); // NON-NLS
        JDOMHelper.setAttributeBoolean(element, "usingWindowSize", isUsingWindowSize()); // NON-NLS
        JDOMHelper.setAttributeColor(element, "background", getBackground()); // NON-NLS
    }

    public void readExternal(Element element) throws JDOMException {
        setImagePath(JDOMHelper.getAttributeString(element,
                                                   "path", // NON-NLS
                                                   DEFAULT_PATH));
        setImageFormat(JDOMHelper.getAttributeString(element,
                                                     "format", // NON-NLS
                                                     DEFAULT_FORMAT));
        setImageWidth(JDOMHelper.getAttributeInt(element, "width", DEFAULT_SIZE));  // NON-NLS
        setImageHeight(JDOMHelper.getAttributeInt(element,
                                                  "height",   // NON-NLS
                                                  DEFAULT_SIZE));
        setUsingWindowSize(JDOMHelper.getAttributeBoolean(element,
                                                          "usingWindowSize",  // NON-NLS
                                                          false));
        setBackground(JDOMHelper.getAttributeColor(element,
                                                   "background", // NON-NLS
                                                   DEFAULT_BACKGROUND));
    }

}
