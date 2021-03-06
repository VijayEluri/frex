/*
 * Created at 06.01.2004 20:53:30
 * Copyright (c) 2004 by Norman Fomferra
 */
package z.core.support.fractals;

import z.core.Fractal;
import z.core.Region;

/**
 * <pre>
 *       z' = z&circ;2 + c
 * </pre>
 */
public final class NormansError2 extends Fractal {

    public Region getStartRegion() {
        if (isJuliaMode()) {
            return new Region(0, 0, 1.6);
        }
        return new Region(-0.1, 0, 1.6);
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

        double zxx, zyy, t;
        int iter;
        for (iter = 0; iter < iterMax; iter++) {
            zxx = zx * zx;
            zyy = zy * zy;
            if (zxx + zyy > rr) {
                return iter;
            }
            zy = 2.0 * zx * zy + cy;
            zx = zxx - zyy + cx;
            if (zx < zy) {
                t = zx;
                zx = zy;
                zy = t;
            }
            xValues[iter] = zx;
            yValues[iter] = zy;
        }
        return iterMax;

    }
}
