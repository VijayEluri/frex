package z.frex.actions;

import z.StringLiterals;
import z.frex.Frex;
import z.ui.FileExtensionFileFilter;
import z.ui.UIUtils;
import z.ui.application.ApplicationWindow;
import z.ui.dialog.MessageDialog;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public class SaveImageAction extends PlaneViewAction {

    private static FileExtensionFileFilter PNG_FILTER = new FileExtensionFileFilter("png", // NON-NLS
                                                                                    StringLiterals.getString("gui.fileTypeDescr.png"),
                                                                                    ".png"); // NON-NLS

    private static FileExtensionFileFilter JPEG_FILTER = new FileExtensionFileFilter("jpeg", // NON-NLS
                                                                                     StringLiterals.getString("gui.fileTypeDescr.jpeg"),
                                                                                     new String[]{".jpeg", ".jpg",}); // NON-NLS

    private static FileExtensionFileFilter WBMP_FILTER = new FileExtensionFileFilter("wbmp", // NON-NLS
                                                                                     StringLiterals.getString("gui.fileTypeDescr.wbmp"),
                                                                                     ".wbmp"); // NON-NLS

    private static FileExtensionFileFilter BMP_FILTER = new FileExtensionFileFilter("bmp", // NON-NLS
                                                                                    StringLiterals.getString("gui.fileTypeDescr.bmp"),
                                                                                    ".bmp"); // NON-NLS

    private static FileExtensionFileFilter GIF_FILTER = new FileExtensionFileFilter("gif", // NON-NLS
                                                                                    StringLiterals.getString("gui.fileTypeDescr.gig"),
                                                                                    ".gif"); // NON-NLS

    public static final String ID = "z.frex.actions.saveImage"; // NON-NLS
    private FileExtensionFileFilter lastImageFormat = PNG_FILTER;
    private final String TITLE = StringLiterals.getString("gui.title.saveImage");

    public SaveImageAction(ApplicationWindow window) {
        super(window, ID);
        setText(StringLiterals.getString("gui.action.text.saveImage"));
        setToolTipText(StringLiterals.getString("gui.action.tooltip.saveImage"));
        setSmallIcon(Frex.getIcon(StringLiterals.getString("gui.action.icon.saveImage")));
    }

    @Override
    public void run() {
        FileExtensionFileFilter[] selectedImageFormat = new FileExtensionFileFilter[] {lastImageFormat};
        File selectedFile = UIUtils.showSafeDialog(getWindow().getShell(),
                                                   TITLE,
                                                   "lastImageDir",  // NON-NLS
                                                   getPlaneView().getPlane().getName(), // NON-NLS
                                                   selectedImageFormat,
                                                   PNG_FILTER,
                                                   JPEG_FILTER,
                                                   WBMP_FILTER,
                                                   BMP_FILTER,
                                                   GIF_FILTER);
        if (selectedFile != null) {
            lastImageFormat = selectedImageFormat[0];
            BufferedImage image = getPlaneView().getImageCanvas().getImage();
            try {
                ImageIO.write(image, selectedImageFormat[0].getFormatName(), selectedFile);
            } catch (IOException e) {
                MessageDialog.showError(getWindow().getShell(),
                                        TITLE,
                                        MessageFormat.format(StringLiterals.getString("gui.msg.errorSavingImage"),
                                                             selectedFile,
                                                             e.getLocalizedMessage()));
            }
        }
    }
}
