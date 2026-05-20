public class Rectangle extends Shape{

    private int id;
    private int length;
    private int height;
    private char pattern;
    private int type = 0;

    public String getSymbol() { return "R"; } //클래스 구분자

    protected String makeString() {
        // 고유 변수인 length, height, pattern을 일렬로.
        return length + "," + height + "," + pattern;
    }

    protected void unpacking(String[] parts) {
        // parts[0] = id, parts[1] = type 이므로, 고유 데이터는 index 2부터 시작합니다.
        this.length = java.lang.Integer.parseInt(parts[2]); // java.lang. 붙이는게.. 제가 라이브러리에 Integer 클래스를 하나 만들어 놔서 그렇습니다..
        this.height = java.lang.Integer.parseInt(parts[3]);
        this.pattern = parts[4].charAt(0); // 첫 글자 꺼내기
    }


    public Rectangle(int _id, int _len, int _h, char _p)
    {
        this.id = _id;
        this.length = _len;
        this.height = _h;
        this.pattern = _p;
    }

    public String toString()
    {
        String str;
        str = "Rectangle" + id + "(" + length + "," + height + "," + pattern + ")";

        return str;
    }

    public void draw()
    {
        for(int i = 0; i < height ; i++)
        {
            for(int j=0; j < length ; j++)
                System.out.print(pattern);

            System.out.println();

        }
    }

    public char getPattern()
    {
        return pattern;
    }

    public void setPattern(char _pattern)
    {
        this.pattern = _pattern;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int _id)
    {
        this.id = _id;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int _length)
    {
        this.length = _length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int _height)
    {
        this.height = _height;
    }

    public int getType(){
        return this.type;
    }

}


