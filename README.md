# Spring   
## Spring 실행 순서
1. run on server(이렇게 호출해야 web.xml을 거쳐서 간다)   
2. web.xml(root-context.xml의 위치, servlet-context.xml의 위치, url-pattern( ex).do ))    
3. root-context.xml(spring에서 사용 할 객체를 생성하는 영역 : New로 만드는 것들)   
4. servletContext.xml(controller를 만드는 영역)   
    * Controller 자동생성
         ```
         <context:component-scan base-package="com.korea.test_di, controller" /> <!--이 부분에 Controller가 존재하는 패키지 추가-->      
         ```
    *  Controller 수동생성    
         ```
         <beans:bean class="controller.BoardController">   
            <beans:property name="service" ref="serviceBean"/> <!-- root-context에 있는 객체를 servlet-context까지 가져오는 작업 -->   
         </beans:bean>   
         ```
5. controller(jsp의 servlet역할)      
6. return되는 jsp가 실행됨( ex)home.jsp )
