package Interview;

public class _面试题_10_05_稀疏数组搜索 {

    public int findString(String[] words, String s) {

        int begain = 0, end = words.length - 1;
        while (begain <= end){
            if (words[begain] == "") {
                begain++;
                continue;
            }
            if (words[end] == "") {
                end--;
                continue;
            }

            int mid = (begain + end) >> 1;
            while (words[mid] == "" && mid <= end) {
                mid ++;
            }

            if (s.compareTo(words[mid]) == 0) return mid;

            if (s.compareTo(words[mid]) > 0)
                begain = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        _面试题_10_05_稀疏数组搜索 cls = new _面试题_10_05_稀疏数组搜索();

        String[] words = {"at", "", "", "", "ball", "","", "sdf","dsdg", "car", "", "", "dad", "", ""};
        int idx = cls.findString(words, "car");
        if (idx == 0){

        }
    }

}
