package z.frex.actions;

import z.StringLiterals;
import z.core.Plane;
import z.frex.Frex;
import z.frex.PlaneView;
import z.ui.ImageCanvas;
import z.ui.application.ApplicationWindow;
import z.ui.dialog.MessageDialog;
import z.util.RegionHistory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class ZoomInteraction extends Interaction {
    public static final String ID = "z.frex.actions.zoom"; // NON-NLS

    public ZoomInteraction(ApplicationWindow window) {
        super(window, ID);
        setText(StringLiterals.getString("gui.interaction.text.zoom"));
        setToolTipText(StringLiterals.getString("gui.interaction.tooltip.zoom"));
        setSmallIcon(Frex.getIcon(StringLiterals.getString("gui.interaction.icon.zoom")));
        setInteractor(new ZoomInteractor());
    }

    private final class ZoomInteractor extends Interactor {
        private Rectangle rectangle;

        private Rectangle imageBounds;

        private Point point;

        @Override
        public void mousePressed(MouseEvent e) {
            PlaneView planeView = getPlaneView();
            ImageCanvas imageCanvas = planeView.getImageCanvas();
            imageBounds = imageCanvas.getImageBounds();
            point = e.getPoint();
            rectangle = new Rectangle(e.getX(), e.getY(), 1, 1);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (rectangle != null) {
                int w = Math.max(Math.abs(e.getX() - point.x),
                                 Math.abs(e.getY() - point.y));
                int h = (w * imageBounds.height) / imageBounds.width;
                rectangle = new Rectangle(point.x - w,
                                          point.y - h,
                                          2 * w,
                                          2 * h);
                getPlaneView().getImageCanvas().repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (rectangle != null && !rectangle.isEmpty()) {
                if (rectangle.width < 5 || rectangle.height < 5) {
                    MessageDialog.showError(getWindow().getShell(),
                                            StringLiterals.getString("gui.title.zoom"),
                                            StringLiterals.getString("gui.msg.sectionTooSmall"));
                } else {
                    final Plane plane = getPlaneView().getPlane();
                    plane.zoomRegion(imageBounds.width,
                                     imageBounds.height,
                                     rectangle.getX() - (double) imageBounds.x,
                                     rectangle.getY() - (double) imageBounds.y,
                                     rectangle.getWidth(),
                                     rectangle.getHeight());

                    RegionHistory regionHistory = Frex.getRegionHistory(plane);
                    if (regionHistory != null) {
                        regionHistory.setCurrentRegion(plane.getRegion());
                    }

                    getPlaneView().generateImage(false);
                }
                rectangle = null;
                imageBounds = null;
                point = null;
            }
        }

        @Override
        public void paintControl(Graphics2D gc) {
            if (rectangle != null) {
                gc.setColor(new Color(255, 255, 255, 50));
                gc.fill(rectangle);
                gc.setColor(Color.WHITE);
                gc.draw(rectangle);
            }
        }
    }
}
