package design_pattern.decorator;

/**
 * ��ʽ��json��������
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 17:28
 */
public class JsonUtil {
    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

    public static String format(String s) {
        int level = 0;
        //��Ÿ�ʽ����json�ַ���
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int index = 0; index < s.length(); index++) {//���ַ����е��ַ�����������
            //��ȡs�е�ÿ���ַ�
            char c = s.charAt(index);
            //level����0����jsonForMatStr�е����һ���ַ�Ϊ\n,jsonForMatStr����\t
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            //����"{"��"["Ҫ���ӿո�ͻ��У�����"}"��"]"Ҫ���ٿո��Զ�Ӧ������","Ҫ����
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }
        return jsonForMatStr.toString();
    }
}
