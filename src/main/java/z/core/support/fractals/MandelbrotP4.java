/*
 * Created at 06.01.2004 20:53:30
 * Copyright (c) 2004 by Norman Fomferra
 */
package z.core.support.fractals;

import z.core.Fractal;
import z.core.Region;

/**
 * <pre>
 *        z' = z&circ;3 + c
 * </pre>
 */
public final class MandelbrotP4 extends Fractal {

    public Region getStartRegion() {
        return new Region(0, 0, 1.6);
    }

    public int compute(final double px,
                       final double py,
                       final double[] xValues,
                       final double[] yValues) {
        final int iterMax = this.iterMax;
        final double rr = bailOutSqr;
        final boolean julia = this.juliaMode;
        final double cx = julia ? juliaCX : px;
        final double cy = julia ? juliaCY : py;
        double zx = julia ? px : 0.0;
        double zy = julia ? py : 0.0;

        double t1, t2;
        int iter;
        for (iter = 0; iter < iterMax; iter++) {
            t1 = zx * zx;
            t2 = zy * zy;
            if (t1 + t2 > rr) {
                return iter;
            }
            t1 = zx * zx - zy * zy;
            t2 = 2.0 * (zx * zy);
            zx = t1 * t1 - t2 * t2 + cx;
            zy = 2.0 * (t1 * t2) + cy;
            xValues[iter] = zx;
            yValues[iter] = zy;
        }
        return iterMax;
    }
}
