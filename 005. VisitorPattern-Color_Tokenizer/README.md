# VIsitor 패턴을 활용한 Color Tokenizer 프로그램아래는 조선대학교 IT 융합대학 1층에 설치되어 있는 음료 자판기입니다.

* Visitor 패턴은 데이터와 연산을 분리하여, 변화가 자주 일어나지 않는 데이터에 대해 빈번하게 추가되는 연산들을 분리하여 객체 상호작용으로 연산을 처리하게 하는 객체지향 설계 방식입니다.
    * 이 패턴은 Iterator에서 다양한 데이터 처리, 컴파일러 등의 다양한 분야에서 활용도가 높은 패턴입니다.

* 문제.
    * Visitor 패턴을 활용하여 Java 소스 코드 파일을 Syntax Coloring이 적용된 웹 문서(Html)로 작성하는 프로그램을 작성하세요.

* 작성된 프로그램은 아래와 같이 실행되어야 합니다.
    *  > java ColorTokenizer <파일 이름>.java
    * 프로그램의 실행결과로 생성되는 파일의 이름은 <파일 이름>.html

* 프로그램은 Java 파일을 파라미터로 받아들여, 아래의 처리를 수행할 수 있어야 합니다.
    * Java의 키워드를 특정한 색으로 표현합니다.
    * 심볼(rlgh)을 특정한 색으로 표현합니다.
     
* html 파일은 아래와 같은 형식이 됩니다.
![image](https://github.com/JeongJeWan/NHN-Academy-Project/assets/85005950/e9bfcf71-28a0-4df4-9696-d4109c62cb83)
