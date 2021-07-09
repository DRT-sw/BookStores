package test;

public class Teet {

}
class Demo{
	private Demo() {}
	private Demo demo = new Demo();
	public Demo getDemo() {
		return demo;
	}
}
class Demo1{
	private Demo1() {}
	private Demo1 demo1 = null;
	public Demo1 getDemo() {
		if (demo1 == null) {
			demo1 = new Demo1();
		}
		return demo1;
	}
}
class Demo2{
	private Demo2() {}
	private Demo2 demo2 = null;
	public  Demo2  getDemo() {
		if (demo2 == null) {
			demo2 = new Demo2();
		}
		return demo2;
	}
}
