package com.fang.formatnumber;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MyRound {

    @Test
    public void testMathRound() {
        /*12.5的四舍五入值：13
         -12.5的四舍五入值：-12*/
        System.out.println("12.5的四舍五入值：" + Math.round(12.5));
        System.out.println("12.5的四舍五入值：" + Math.round(12.4));
        System.out.println("-12.5的四舍五入值：" + Math.round(-12.5));
    }


/*    银行家涉入法了。该算法是由美国银行家提出了，主要用于修正采用上面四舍五入规则而产生的误差。如下：

    舍去位的数值小于5时，直接舍去。

    舍去位的数值大于5时，进位后舍去。

    当舍去位的数值等于5时，若5后面还有其他非0数值，则进位后舍去，若5后面是0时，则根据5前一位数的奇偶性来判断，奇数进位，偶数舍去。

    对于上面的规则我们举例说明

         11.556 = 11.56 ------六入

         11.554 = 11.55 -----四舍

         11.5551 = 11.56 -----五后有数进位

         11.545 = 11.54 -----五后无数，若前位为偶数应舍去

         11.555 = 11.56 -----五后无数，若前位为奇数应进位 */
    public void test2() {
        BigDecimal d = new BigDecimal(10000000);
        BigDecimal r = new BigDecimal(0.001875 * 3);
        BigDecimal i = d.multiply(r).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        System.out.println("季利息是： " + i);
    }

    /**
     1、 ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。

     2、 ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。

     3、 ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于ROUND_UP，若为负数，舍入行为类似于ROUND_DOWN。Math.round()方法就是使用的此模式。

     4、 ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于ROUND_DOWN；若为负数，舍入行为类似于ROUND_UP。

     5、 HALF_UP：最近数字舍入(5进)。这是我们最经典的四舍五入。

     6、 HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。

     7、 HAIL_EVEN：银行家舍入法。
     */

    /**
     * 四舍五入
     * 在这里使用BigDecimal ，并且采用setScale方法来设置精确度，同时使用RoundingMode.HALF_UP表示使用最近数字舍入法则来近似计算。在这里我们可以看出BigDecimal和四舍五入是绝妙的搭配。
     */
    @Test
    public void test3() {
        double f = 111231.5585;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


    /**
     * 方式二：#.00 表示两位小数 #.0000四位小数 以此类推…
     */
    @Test
    public void  test4() {
        double f = 111231.5585;
        DecimalFormat df = new DecimalFormat("#.00");
        String s = df.format(f);
    }

    /**
     * %.2f %. 表示 小数点前任意位数   2 表示两位小数 格式后的结果为f 表示浮点型
     */
    @Test
    public void test5() {
        double f = 111231.5585;
        String result = String.format("%.2f");
    }

    @Test
    public void  test6() {
        Integer i1 = 64;
        Integer i2 =74;

        BigDecimal bd = new BigDecimal(i1);
        System.out.println(bd.setScale(2, RoundingMode.HALF_UP).doubleValue());

    }

    @Test
    public void  test7() {
        Integer i1 = 0;
        BigDecimal bd = new BigDecimal(0.0);
        System.out.println(i1 + 1);
    }

}
