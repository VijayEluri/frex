package z.frex.actions;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import z.StringLiterals;
import z.core.IColorizer;
import z.core.support.colorizers.PaletteColorTable;
import z.frex.Frex;
import z.frex.PlaneView;
import z.frex.dialogs.EditColorsDialog;
import z.ui.FileExtensionFileFilter;
import z.ui.UIUtils;
import z.ui.dialog.MessageDialog;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;

public class SafeColorsAction extends ApplicationWindowAction {
    public static final String ID = "z.frex.actions.storeColors"; // NON-NLS
    private final PlaneView view;
    private final EditColorsDialog editColorsDialog;

    public SafeColorsAction(PlaneView view, EditColorsDialog editColorsDialog) {
        super(view.getPage().getWindow(), ID);
        this.view = view;
        this.editColorsDialog = editColorsDialog;
        setText(StringLiterals.getString("gui.action.text.storeColors"));
        setToolTipText(StringLiterals.getString("gui.action.tooltip.storeColors"));
        setSmallIcon(Frex.getIcon(StringLiterals.getString("gui.action.icon.save")));
    }

    @Override
    public void run() {
        PaletteColorTable paletteColorTable = editColorsDialog.getEditColorsModel().getCurrentPaletteColorTable();

        File file = promptForFile();
        if (file == null) {
            return;
        }

        Element element = new Element(IColorizer.ELEMENT_NAME);
        try {
            paletteColorTable.writeExternal(element);
            final XMLOutputter xmlOutputter = new XMLOutputter();
            xmlOutputter.setFormat(Format.getPrettyFormat());

            FileWriter fileWriter = new FileWriter(file);
            try {
                xmlOutputter.output(element, fileWriter);
            } finally {
                fileWriter.close();
            }

        } catch (JDOMException e) {
            showError(file, e);
        } catch (IOException e) {
            showError(file, e);
        }

        int numColors = paletteColorTable.getNumColors();
        System.out.println("int[][] colors = new int[" + numColors + "][] {");   // NON-NLS
        for (int i = 0; i < numColors; i++) {
            int rgba = paletteColorTable.getRgba(i);
            Color color = new Color(rgba);
            System.out.printf("    {%d, %d, %d, %d},%n",    // NON-NLS
                              color.getRed(),
                              color.getGreen(),
                              color.getBlue(),
                              color.getAlpha());
        }
        System.out.println("};"); // NON-NLS


/*
        File file = (File) colorizer;
        try {
            plane.write(file);
            BufferedImage image = getPlaneView().getImageCanvas().getImage();
            // todo: make it a preference setting, if a quicklook shall be always be stored
            boolean saveQuicklook = true;
            if (saveQuicklook) {
                writeQuicklookFile(file, image);
            }
        } catch (Exception e) {
            openError(file, e);
            return false;
        }
*/
    }

    private File promptForFile() {
        return UIUtils.showSafeDialog(getWindow().getShell(),
                                      StringLiterals.getString("gui.title.storeColors"),
                                      OpenColorsAction.LAST_COLORS_DIR_PROPERTY,
                                      view.getPlane().getName(),
                                      new FileExtensionFileFilter[1],
                                      OpenColorsAction.FILE_FILTER);
    }

    public void showError(File file, Exception e) {
        e.printStackTrace();
        MessageDialog.showError(getWindow().getShell(),
                                StringLiterals.getString("gui.title.saveFile"),
                                MessageFormat.format(StringLiterals.getString("gui.msg.errorSavingFile"),
                                                     file.getPath(),
                                                     e.getClass().getName(),
                                                     e.getLocalizedMessage()));
    }

}
