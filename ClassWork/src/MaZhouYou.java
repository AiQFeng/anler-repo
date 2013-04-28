import java.util.Stack;

public class MaZhouYou {
	static int SIZE = 5;								//棋盘行列数
	static int BEGINROW = 0;							//起始位置所在行下标
	static int BEGINCOLUMN = 0;						//起始位置所在列下标
	static int[][] matrix = new int[SIZE][SIZE];
	static Stack<Position> stack = new Stack<Position>();
	static int count = 0;

	
	public static void main(String[] args) {
		matrix = init();
		
		Position now = chooseBeginPosition();
		
		if (findAWay(now)) {
			outputStack();
		}else {
			System.out.println(SIZE+ "*" +SIZE+ "，初始位置：[" +BEGINROW+"]["+BEGINCOLUMN+"] 没有路径可以遍历");
		}		
	}
	
	
	/**
	 * 生成行、列数为SIZE的棋盘，初始值均赋为0
	 * 
	 * @return  行列数为SIZE的二维数组
	 */
	public static int[][] init(){
		int[][] matrix = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] = 0;
			}
		}
		return matrix;
	}	
	
	
	/**
	 * 设定初始位置 
	 */
	public static Position chooseBeginPosition() {
		return new Position(BEGINROW, BEGINCOLUMN);
	}
	
	
	/**
	 * 寻找遍历棋盘的路径
	 * 
	 * @param now  现在所在棋盘上位置
	 * @return		如果有遍历路径，返回true；否则返回false
	 */
	public static boolean findAWay(Position now) {		
		
		while (true) {
			
			if (count == SIZE * SIZE -1) {					 //如果当前栈已存放24个位置，表明有路径能够遍历
				matrix[now.row][now.column] = SIZE * SIZE;	
				stack.push(now);							 //把最后一格放进栈
				return true;
			}
			
			Position next;
			
			if (now.direction < 8) {						 //判断栈顶弹出的位置，8个方向如果没有全部尝试
				next = getNextPosition(now);				 //获取下个位置
		    }else {
				next = null	;								 //如果全部尝试过，下个位置设为null
			}
			
			while (next == null || !canGoTo(next) ) {      //如果下一步的位置超出棋盘，或者已经走过，
				if (now.direction < 7) {					
					now.direction++;						//方向+1 继续判断下一步位置
					next = getNextPosition(now);
				}else {										//若8个方向全部尝试过，则跳出循环
					break;							
				}								
			}
			
			if (next != null && canGoTo(next)) {			//如果找到下一步可以跳的位置
				Position inStack = new Position();
				inStack = now;								//当前位置保存进栈
				inStack.direction++;						//下一步要走的方向保存进栈
				stack.push(inStack);				
				count++;									//栈内存放的位置数+1
				matrix[now.row][now.column] = count;		//棋盘上当前位置的值设置为count
				now = next;									//跳到下一格
				
			} else {										//如果下一步没有可以跳的位置
				if (stack.isEmpty()) {						//判断栈是否空，若空则表示没有遍历路径，return false
					return false;
				}
				matrix[now.row][now.column] = 0;			//若栈不为空，把当前位置的值设为0
				now = stack.pop();							//从栈顶弹出上一步的位置，返回到上一步所在位置
				count--;									//栈内存放的位置数-1
			}
				
		}
	}
	
	
	/**
	 * increment中存放8个不同方向的行与列坐标增量
	 * 
	 * @param now  现在所在的位置
	 * @return		如果下一步的位置没有超出棋盘范围，返回Position next，如果超出棋盘返回null
	 */
	public static Position getNextPosition(Position now){
		Position next = new Position();
		
		// 数组increment[0]代表行增量，increment[1]代表列增量
		int[][] increment = {{-1, -2},{-2, -1},{-2, +1},{-1, +2},{+1, +2},{+2, +1},{+2, -1},{+1, -2}};
		next.row = now.row + increment[now.direction][0];
		next.column = now.column + increment[now.direction][1];
		
		if (next.row >= 0 && next.row < SIZE && next.column >= 0 && next.column < SIZE) {
			return next;
		}		
		return null;
	}
	
	
	/**
	 * 判断position所在位置是否已经走过
	 * 
	 * @param position  要判断的位置
	 * @return			 如果没有走过，返回true；否则返回false
	 */
	public static boolean canGoTo(Position position) {
		if (matrix[position.row][position.column] == 0) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * 打印整个棋盘，每一步走的位置用数字标识
	 */
	public static void outputStack() {
		String string = "棋盘规格：" + SIZE + "X" + SIZE + " ; 初始位置[" + BEGINROW + "][" + BEGINCOLUMN + "]";
		System.out.println(string);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.printf("%3d", matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println("---------------------------------------");
	}
}


class Position{
	int row = 0;		//棋盘上的横坐标
	int column = 0;		//棋盘上的纵坐标
	int direction = 0;	//每一步共有8个方向可以跳，用0-7表示，0表示左上方10点钟方向的位置，按照顺时针方向增加	
	
	Position(){		
	}	
	
	Position(int r, int col){
		this.row = r;
		this.column = col;
	}
}

