package 其他;

public class _831_隐藏个人信息 {

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

    // 先把字符串中的非数字全部删除。 拼接 "***-***-" 和 最后四位数字。
    // 判断是否有区号,有区号则替换。
    static String dealPhone(String phoneStr){

        String digits = phoneStr.replaceAll("\\D+", "");
        String local = "***-***-" + digits.substring(digits.length() - 4);
        if (digits.length() == 10) return local;
        String ans = "+";
        for (int i = 0; i < digits.length() - 10; ++i)
            ans += "*";
        return ans + "-" + local;
    }

}
