# 09. try-finally보다는 try-with-resources를 사용하라
> InputStream, OutputStream, Connection 등 close()를 직접 호출해 닫아줘야 하는 자원이 많이 있습니다. <br/>
> 자원 닫기는 클라이언트가 놓칠수 있기 때문에 예측할 수 없는 성능문제로 이어지기도 합니다. <br/>
> 이러한 자원중 상당수가 안전망으로 finalizer(item8)을 사용하고 있지만, finalizer는 그리 믿을만하지 못합니다.



### 전통적인 방식 (try-finally)
```java
public String getTryFinallyRead(String path) throws IOException {
    BufferedReader br = null;

    try {
        br = new BufferedReader(new FileReader(path));
        return br.readLine();
    } catch (Exception e) {
        e.getMessage();
    } finally {
       br.close();
    }

    return path;
}
```
* 난해한 코드가 작성 되었습니다.
* 예외는 try 블록과 finally 블록 모두에서 발생할 수 있습니다.
* 물리적인 문제가 발생한다면 try, finally에서 모두 예외가 발생합니다.
* 하지만, 이런 경우 두번째 예외가 첫번째 문제를 삼켜서 실제 시스템에서 버그 추적이 어려워 질 수도 있습니다.

---
### 대안점은?
* 자바7이 투척한 try-with-resource를 사용
* AutoCloseable를 구현하고 close method()를 사용할 것
```java
public String getTryWithResourcesRead(String path) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
       return br.readLine();
    }
}

```
* 코드가 간결해졌습니다. (AutoClosable을 구현하여 짧게 사용이 가능합니다)
* try-with-resources를 이용하면 첫번째 예외부터 추적이 가능해집니다.
* 뿐만아니라 스택 추적 영역으로 예외를 추적할 수 있습니다.

### 사용방법
try() 내부에 AutoCloseable를 구현한 클래스를 사용하면 됩니다 

---
## 결론
* try-finally보다는 try-with-resources를 사용하는 것이 좋습니다.
* 코드는 짧고 명확해지고, 예외 정보도 더욱 유용합니다.
