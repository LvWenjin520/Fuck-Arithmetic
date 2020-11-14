package datacunstructer;

public class MyStack {
	public static void main(String[] args) {
		
		String expression = "7*2*2-5+1-5+3-4";
		ArrayStack numStack = new ArrayStack(10);
		ArrayStack operStack = new ArrayStack(10);
		
		int index = 0;
		int num1,num2 = 0;
		int oper = 0;
		int res = 0;
		
		char ch = ' ';
		//多位数
		String keepNum = "";
		
		while(true) {
			//获取每一个字符
			ch = expression.substring(index,index+1).charAt(0);
			//如果是运算符的话
			if(operStack.isOper(ch)) {
				//栈中已经存在操作符的时候
				if(!operStack.isEmpty()) {
					//如果当前的优先级没有原来的优先级高
					if(operStack.priority(ch)<= operStack.priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, ch);
						numStack.push(res);
						operStack.push(ch);
					//如果优先级高的话
					}else {
						operStack.push(ch);
					}
				}else {
					operStack.push(ch);
				}
			//如果是数字的话
			}else {
				keepNum+=ch;
				if(index == expression.length()-1) {
					numStack.push(Integer.parseInt(keepNum));
				}else {
					if(operStack.isOper(expression.substring(index+1, index+2).charAt(0))) {
						numStack.push(Integer.parseInt(keepNum));
						keepNum="";
					}
				}
			}
			
			index++;
			if(index>=expression.length()) {
				break;
			}
		}
		while(true) {
			if(operStack.isEmpty()) {
				break;
			}
			
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);
		}
		
		int res2 = numStack.pop();
		System.out.println("结果为："+res2);
	}
}




class ArrayStack{
	
	private int maxSize;
	private int top=-1;
	private int[] arrStack;
	
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		arrStack = new int[maxSize];
	}
	
	public boolean isFull() {
		return top == maxSize-1;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	/**
	 * 获取嘴上层的数据，但不是pop
	 * @return
	 */
	public int peek() {
		return arrStack[top];
	}
	
	public void push(int data) {
		if(isFull()) {
			System.out.println("栈已满");
			return;
		}
		top++;
		arrStack[top] = data;
	}
	
	public int size() {
		return top+1;
	}

	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("空栈");
		}
		
		int temp = arrStack[top];
		top--;
		return temp;
	}
	
	/**
	 * 判断符号的优先级
	 * @param oper
	 * @return
	 */
	public int priority(int oper) {
		if(oper == '*' || oper == '/') {
			return 1;
		}else if(oper == '+' || oper == '-') {
			return 0;
		}else {
			return -1;
		}
	}
	
	/**
	 * 判断是否为运算符
	 * @param val
	 * @return
	 */
	public boolean isOper(char val) {
		return val=='+'||val=='-'||val=='*'||val=='/';
	}
	
	public int cal(int num1,int num2,int oper) {
		int result = 0;
		switch(oper) {
			case'+':
				result = num1+num2;
				break;
			case'-':
				result = num2-num1;
				break;
			case'*':
				result = num1*num2;
				break;
			case'/':
				result = num2/num1;
				break;
			default:
				break;
		}
		
		return result;
	}
	
}
