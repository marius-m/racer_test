package lt.markmerkk.app.utils;

/*******************************************************************************
 * Copyright (c) 2001, 2008 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/cpl-v10.html
 *
 * Contributors:
 *     Mathew A. Nelson
 *     - Initial API and implementation
 *     Flemming N. Larsen
 *     - Moved all methods to classes like FileUtil, StringUtil, WindowUtil,
 *       Logger etc. exception for the following methods, which have been kept
 *       here as legacy robots make use of these methods:
 *       - normalAbsoluteAngle()
 *       - normalNearAbsoluteAngle()
 *       - normalRelativeAngle()
 *     - The isNear() was made public
 *     - Optimized and provided javadocs for all methods
 *******************************************************************************/



import static java.lang.Math.PI;
import java.util.Random;


/**
 * Utility class that provide methods for normalizing angles.
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */

public class Utils {
    private final static double TWO_PI = 2 * PI;
    /**
     * Normalizes an angle to a relative angle.
     * The normalized angle will be in the range from -PI to PI, where PI
     * itself is not included.
     *
     * @param angle the angle to normalize
     * @return the normalized angle that will be in the range of [-PI,PI[
     */
    public static double normalRelativeAngle(double angle) {
        return (angle %= TWO_PI) >= 0 ? (angle < PI) ? angle : angle - TWO_PI : (angle >= -PI) ? angle : angle + TWO_PI;
    }
}
