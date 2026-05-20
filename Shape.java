abstract public class Shape {

    //공통적으로 type 변수도 있어야할듯.

    abstract void draw();

    abstract int getId();

    abstract void setId(int id);

    //게터 세터 왜 쓰는지 효율적으로 보여주는 부분, 다운캐스팅 안쓰고 자식 객체 변수 가져오기

    abstract int getType();

    //저장 불러오기 기능을 위해 파싱 기능을 자식 클래스들이 가지도록 하기.
    public abstract String getSymbol(); // 클래스 구분
    protected abstract String makeString(); //멤버변수, 데이터들을 문자열로 만들기
    protected abstract void unpacking(String[] parts);//불러오기, 저장된 문자열들 변환

    public final String makeit() {
        return getSymbol() + ":" + getId() + "," + getType() + "," + makeString();
    }

    public final void deserialize(String entryData) {
        String[] parts = entryData.split(","); //유용한 메서드1. entryData.split , 구분자를 기준으로 데이터를 분리해 배열에 저장해줌.

        // 1단계: 모든 도형의 공통 데이터 복원
        setId(java.lang.Integer.parseInt(parts[0])); // 유용한 메서드 2. parseInt("100"), String을 int 로 바꿔줌 toString 반대
        // type은 각 클래스에 이미 상수로 고정되어 있으므로 굳이 대입하지 않아도 무방합니다.

        // 2단계: 자식 클래스에게 나머지 데이터 파싱 책임을 넘김
        unpacking(parts);
    }
}
}
