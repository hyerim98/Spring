# Spring   
## Spring 실행 순서
1. run on server(이렇게 호출해야 web.xml을 거쳐서 간다)   
2. web.xml(root-context.xml의 위치, servlet-context.xml의 위치, url-pattern( ex).do )설정)    
3. root-context.xml(spring에서 사용 할 객체를 생성하는 영역 : New로 만드는 것들)   
4. servletContext.xml(controller를 만드는 영역)   
    * Controller 자동생성
         ```
         <!-- Component를 가지고 있는 패키지만 자동생성이 가능 -->
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

## Annotation   
* Component를 가지고 있는 것 : @Repository, @Controller, @Service (Auto-Detecting.자동생성이 가능)   
* @Repository   
   * @Repository를 해당 class위에 붙이면 bean에서 객체를 생성하지 않아도 자동으로 class가 객체화됨 생성됨   
   *  이는 setter&constructor Injection을 사용할 수 없다   
   *  그러므로 class안에서 객체가 필요하다면 해당 객체에 Autowired기능을 사용해야 한다
* @Autowired   
   * 자동주입(자동연결,자동Injection)   
   * spring의 4개 영역을 객체 생성없이 가져와 사용할 수 있도록 처리(request, session 등)
   * 따로 new를 작성하지 않아도 객체 사용이 가능하다  
controller와 dao가 servlet-context.xml에서 이렇게 자동생성이 되려면, dao와 controller가 Component를 가지고있는 Annotaion이 선언되어 있어야한다 
 ```
   <!-- Component를 가지고 있는 패키지 자동생성 -->
   <!-- 자동생성은 따로 코드를 작성하지 않아도 Autowired를 자동으로 사용할 수 있다 -->
   <context:component-scan base-package="dao, controller" />
 ```   
 dao는 @Repository가 선언되어있다
 ```
 //외부에서 현재 DAO를 식별할 수 있도록 별칭을 지정해준다 -> dept_dao
@Repository("dept_dao") // 데이터 관리 객체로써 해당 어노테이션이 명시되어 있어야 자동생성이 가능-> Bean객체 만들 필요없음
public class DeptDAO {
	
	/*
	 * 현재 DeptDAO는 servlet-context에서 auto-detecting을 통해 자동완성 되고 있다
	       이는 setter&constructor Injection을 사용할 수 없다
	       그러므로 sqlsession이 필요하다면 Autowired기능을 사용해야 한다
	*/
	@Autowired // sqlsession을 bean객체를 생성하지 않으니 sqlsession를 자동으로 객체화하는 작업
	SqlSession sqlsession;
	
	public DeptDAO() {
		System.out.println("-------DeptDAO 생성자-------");
	}
	
	public List<DeptVO> selectList(){
		List<DeptVO> list = sqlsession.selectList("dept.dept_list");
		return list;
	}
}
 ```   
 controller는 @Controller가 선언되어있다   
 ```
 /*
 * @Repository, @Controller, @Service객체를 Component라고 한다 -> Auto-Detecting(자동생성)이 가능
*/

@Controller
public class TestController {
	@Autowired // -> new하면서 자동으로 만들어진다
	DeptDAO dept_dao; // DeptDAO class의 @Repository("dept_dao") 별칭 이름과 반드시 일치시켜야한다
	
	public TestController() {
		System.out.println("---TestController 생성자---");
	}
	
	@RequestMapping(value= {"/","/list.do"})
	public String dept_list(Model model) {
		List<DeptVO> dept_list = dept_dao.selectList();
		model.addAttribute("list",dept_list);
		return "total_list";
	}
}
```
   

** spring file upload : source -> Ex_1214_SpringFileUpload **
