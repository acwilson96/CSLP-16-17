class classTester {
	
	public static void main(String args[]) {
		testClass();
	}

	public void testClass() {
		float afloat = 64;
		float bfloat = 50;
		bin binA = new bin(0, afloat, afloat, 5, afloat, afloat, afloat, afloat);
		System.out.println("binA = " + binA.timeOfNextBag);
		System.out.println();
		bin binB = new bin(1, bfloat, bfloat, 5, bfloat, bfloat, bfloat, bfloat);
		System.out.println("binB = " +binB.timeOfNextBag);
		System.out.println();
		System.out.println("binA = " +binA.timeOfNextBag);
		System.out.println("binB = " +binB.timeOfNextBag);
	}
	
}