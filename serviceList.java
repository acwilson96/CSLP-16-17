import java.util.*;

class serviceList {

	public ArrayList<Integer> serviceListBins = new ArrayList<Integer>();

	public serviceList(int[] array) {
		for (int i = 0; i < array.length; i++) {
			serviceListBins.add(array[i]);
		}
	}

	public int getElem(int i) {
		return this.serviceListBins.get(i);
	}

	public ArrayList<Integer> toArrayList() {
		// Nothing
		return this.serviceListBins;
		//
	}

}