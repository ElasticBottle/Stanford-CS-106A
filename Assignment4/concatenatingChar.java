import acm.program.*;
import acm.program.ConsoleProgram;


public class concatenatingChar extends ConsoleProgram {
	public void run() {
		String a, b;
		a = readLine("enter first char: ");
		b= readLine ("enter second char: ");
		
		println(a.charAt(0)+b.charAt(0)); 
	}
}
