package 其他.模拟面试1;

public class _邮箱电话号码加密 {

    static String maskPII(String S) {

        if (S.contains("@")){
            // 邮箱
            return dealMail(S);
        }else{
            // 电话号码
            return dealPhone(S);
        }
    }

    static String dealMail(String mailS){
        mailS =  mailS.toLowerCase();

        int idx = mailS.indexOf("@");
        String str1 = mailS.substring(idx,mailS.length());

        String s1 = mailS.substring(0,1);
        String s2 = mailS.substring(idx - 1, idx);

        StringBuilder sb = new StringBuilder();
        sb.append(s1).append("*****").append(s2).append(str1);

        return sb.toString();
    }

    static String dealPhone(String phoneStr){

        String digits = phoneStr.replaceAll("\\D+", "");
        String local = "***-***-" + digits.substring(digits.length() - 4);
        if (digits.length() == 10) return local;
        String ans = "+";
        for (int i = 0; i < digits.length() - 10; ++i)
            ans += "*";
        return ans + "-" + local;
    }

    public static void main(String[] args){
        System.out.println(maskPII("+111 111 111 1111"));
    }
}
