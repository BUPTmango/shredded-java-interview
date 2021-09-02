/**
 * m匹马，n个赛道，求前k快的马（M>N>k）
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/9/2 16:47
 */
public class HorseRacing {
    public static void main(String[] args) {
        System.out.println(hourseRace(64, 8, 4));
        System.out.println(hourseRace(64, 6, 4));
        System.out.println(hourseRace(25, 5, 3));
        System.out.println(hourseRace(16, 4, 4));
//        System.out.println(findCount(11, 6, 6));
    }

    private static int hourseRace(int m, int n, int k) {
        // 不能整除需要多赛一组
        int groups = m % n == 0 ? m / n : m / n + 1;
        int result = groups;
        // 如果每组第一一次赛不完
        if (groups > n) {
            // 递归 转化为groups匹马
            result += hourseRace(groups, n, k);
        } else {
            // 每组第一赛一次
            result++;
            // 剩下需要赛的马的数量
        }
        int remain = (1 + k - 1) * (k - 1) / 2 + k - 1;
        // 如果剩下的一次赛不完
        if (remain > n) {
            // 最少情况就是一次就挑中最快的k
            result++;
        } else {
            result++;
        }
        return result;
    }
}
