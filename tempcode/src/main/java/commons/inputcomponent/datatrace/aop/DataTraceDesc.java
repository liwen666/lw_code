package commons.inputcomponent.datatrace.aop;

@java.lang.annotation.Target(value={java.lang.annotation.ElementType.METHOD})
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Inherited
@java.lang.annotation.Documented
public @interface DataTraceDesc {
	String value() default ""; 
}
