class bin {

	public static float binVolume;
	public static float disposalDistrRate;
	public static int disposalDistrShape;
	public static float bagVolume;
	public static float bagWeightMin;
	public static float bagWeightMax;

	public bin(float binVolume, float disposalDistrRate, int disposalDistrShape, float bagVolume, float bagWeightMin, float bagWeightMax) {
		this.binVolume			=	binVolume;
		this.disposalDistrRate	=	disposalDistrRate;
		this.disposalDistrShape	=	disposalDistrShape;
		this.bagVolume			=	bagVolume;
		this.bagWeightMin		=	bagWeightMin;
		this.bagWeightMax		=	bagWeightMax;
	}
	
}