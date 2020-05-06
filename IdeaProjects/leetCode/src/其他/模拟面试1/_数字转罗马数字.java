package 其他.模拟面试1;

public class _数字转罗马数字 {

    static String intToRoman(int num) {

        StringBuilder sb = new StringBuilder();
        return intToRoman(sb,num);
    }

    static String intToRoman(StringBuilder sb,int num){

        if (num == 0) return sb.toString();
        if (num >= 1000){
            sb.append("M");
            num -= 1000;
        }else if (num >= 500){
            if (num >= 900){
                sb.append("CM");
                num -= 900;
            }else {
                sb.append("D");
                num -= 500;
            }
        }else if (num >= 100){
            if (num >= 400){
                sb.append("CD");
                num -= 400;
            }else {
                sb.append("C");
                num -= 100;
            }
        }else if (num >= 50){
            if (num >= 90){
                sb.append("XC");
                num -= 90;
            }else {
                sb.append("L");
                num -= 50;
            }
        }else if (num >= 10){
            if (num >= 40){
                sb.append("XL");
                num -= 40;
            }else {
                sb.append("X");
                num -= 10;
            }
        }else if (num >= 5){
            if (num >= 9){
                sb.append("IX");
                num -= 9;
            }else {
                sb.append("V");
                num -= 5;
            }
        }else if (num >= 1){
            if (num >= 4){
                sb.append("IV");
                num -= 4;
            }else {
                sb.append("I");
                num -= 1;
            }
        }

        return intToRoman(sb,num);
    }

    public static void main(String[] args){
        System.out.println(intToRoman(3999));
    }
}
