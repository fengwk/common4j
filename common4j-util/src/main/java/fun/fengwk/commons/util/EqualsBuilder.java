package fun.fengwk.commons.util;

import java.util.Arrays;
import java.util.Objects;

/**
 * <pre>
 * public boolean equals(Object obj) {
 *     if (this == obj) { return true; }
 *     if (obj == null) { return false; }
 *     if (getClass() != obj.getClass()) { return false; }
 *     Obj other = (Obj) obj;
 *     return new EqualsBuilder()
 *                 .appendSuper(super.equals(obj))
 *                 .append(f1, other.f1)
 *                 .append(f2, other.f2)
 *                 .append(f3, other.f3)
 *                 .build();
 * }
 * </pre>
 * 
 * @author fengwk
 */
public class EqualsBuilder {

    private boolean isEquals = true;
    
    public EqualsBuilder appendSuper(boolean superEquals) {
        if (isEquals) {
            isEquals = superEquals;
        }
        return this;
    }
    
    public EqualsBuilder append(byte f1, byte f2) {
        if (isEquals) {
            isEquals = f1 == f2;
        }
        return this;
    }
    
    public EqualsBuilder append(short f1, short f2) {
        if (isEquals) {
            isEquals = f1 == f2;
        }
        return this;
    }
    
    public EqualsBuilder append(int f1, int f2) {
        if (isEquals) {
            isEquals = f1 == f2;
        }
        return this;
    }
    
    public EqualsBuilder append(long f1, long f2) {
        if (isEquals) {
            isEquals = f1 == f2;
        }
        return this;
    }
    
    public EqualsBuilder append(float f1, float f2) {
        if (isEquals) {
            isEquals = Float.floatToIntBits(f1) == Float.floatToIntBits(f2);
        }
        return this;
    }
    
    public EqualsBuilder append(double f1, double f2) {
        if (isEquals) {
            isEquals = Double.doubleToLongBits(f1) == Double.doubleToLongBits(f2);
        }
        return this;
    }
    
    public EqualsBuilder append(char f1, char f2) {
        if (isEquals) {
            isEquals = f1 == f2;
        }
        return this;
    }
    
    public EqualsBuilder append(boolean f1, boolean f2) {
        if (isEquals) {
            isEquals = f1 == f2;
        }
        return this;
    }
    
    public EqualsBuilder append(byte[] f1, byte[] f2) {
        if (isEquals) {
            isEquals = Arrays.equals(f1, f2);
        }
        return this;
    }
    
    public EqualsBuilder append(short[] f1, short[] f2) {
        if (isEquals) {
            isEquals = Arrays.equals(f1, f2);
        }
        return this;
    }
    
    public EqualsBuilder append(int[] f1, int[] f2) {
        if (isEquals) {
            isEquals = Arrays.equals(f1, f2);
        }
        return this;
    }
    
    public EqualsBuilder append(long[] f1, long[] f2) {
        if (isEquals) {
            isEquals = Arrays.equals(f1, f2);
        }
        return this;
    }
    
    public EqualsBuilder append(float[] f1, float[] f2) {
        if (isEquals) {
            isEquals = Arrays.equals(f1, f2);
        }
        return this;
    }
    
    public EqualsBuilder append(double[] f1, double[] f2) {
        if (isEquals) {
            isEquals = Arrays.equals(f1, f2);
        }
        return this;
    }
    
    public EqualsBuilder append(char[] f1, char[] f2) {
        if (isEquals) {
            isEquals = Arrays.equals(f1, f2);
        }
        return this;
    }
    
    public EqualsBuilder append(boolean[] f1, boolean[] f2) {
        if (isEquals) {
            isEquals = Arrays.equals(f1, f2);
        }
        return this;
    }
    
    public <F> EqualsBuilder append(F f1, F f2) {
        if (isEquals) {
            isEquals = Objects.equals(f1, f2);
        }
        return this;
    }
    
    public <F> EqualsBuilder append(F[] f1, F[] f2) {
        if (isEquals) {
            isEquals = Arrays.deepEquals(f1, f2);
        }
        return this;
    }
    
    public boolean build() {
        return isEquals;
    }

}
