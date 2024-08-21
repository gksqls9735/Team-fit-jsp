package model;

public class MealVO {
	private String meal_clsf_nm; // 식사의 분류
	private String meal_nm; // 음식이름
	private String cook_mth_cont; // 레시피
	private String matrl_nm; // 재료
	private double calorie_qy; // kcal
	private double carboh_qy; // 탄수화물
	private double protein_qy; // 단백질
	private double fat_qy; // 지방
	private double cellu_qy; // 섬유질
	private double calcium_qy; // 칼슘
	private double phosph_qy; // 인
	private double fe_qy; // 철분
	private double natrium_qy; // 나트륨
	private double potassium_qy; // 칼륨
	private double vitamina_qy; // 비타민A
	private double thiamin_qy; // 비타민B1(티아민)
	private double riboflamin_qy; // 비타민B2(리보플라빈)
	private double niacin_qy; // 비타민B3(니아신)
	private double vitaminc_qy; // 비타민C
	private String meal_pictr_file_nm; // 사진 파일 이름 사용 안 함

	public MealVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MealVO(String meal_clsf_nm, String meal_nm, String cook_mth_cont, String matrl_nm, double calorie_qy,
			double carboh_qy, double protein_qy, double fat_qy, double cellu_qy, double calcium_qy, double phosph_qy,
			double fe_qy, double natrium_qy, double potassium_qy, double vitamina_qy, double thiamin_qy,
			double riboflamin_qy, double niacin_qy, double vitaminc_qy, String meal_pictr_file_nm) {
		super();
		this.meal_clsf_nm = meal_clsf_nm;
		this.meal_nm = meal_nm;
		this.cook_mth_cont = cook_mth_cont;
		this.matrl_nm = matrl_nm;
		this.calorie_qy = calorie_qy;
		this.carboh_qy = carboh_qy;
		this.protein_qy = protein_qy;
		this.fat_qy = fat_qy;
		this.cellu_qy = cellu_qy;
		this.calcium_qy = calcium_qy;
		this.phosph_qy = phosph_qy;
		this.fe_qy = fe_qy;
		this.natrium_qy = natrium_qy;
		this.potassium_qy = potassium_qy;
		this.vitamina_qy = vitamina_qy;
		this.thiamin_qy = thiamin_qy;
		this.riboflamin_qy = riboflamin_qy;
		this.niacin_qy = niacin_qy;
		this.vitaminc_qy = vitaminc_qy;
		this.meal_pictr_file_nm = meal_pictr_file_nm;
	}

	public String getMeal_clsf_nm() {
		return meal_clsf_nm;
	}

	public void setMeal_clsf_nm(String meal_clsf_nm) {
		this.meal_clsf_nm = meal_clsf_nm;
	}

	public String getMeal_nm() {
		return meal_nm;
	}

	public void setMeal_nm(String meal_nm) {
		this.meal_nm = meal_nm;
	}

	public String getCook_mth_cont() {
		return cook_mth_cont;
	}

	public void setCook_mth_cont(String cook_mth_cont) {
		this.cook_mth_cont = cook_mth_cont;
	}

	public String getMatrl_nm() {
		return matrl_nm;
	}

	public void setMatrl_nm(String matrl_nm) {
		this.matrl_nm = matrl_nm;
	}

	public double getCalorie_qy() {
		return calorie_qy;
	}

	public void setCalorie_qy(double calorie_qy) {
		this.calorie_qy = calorie_qy;
	}

	public double getCarboh_qy() {
		return carboh_qy;
	}

	public void setCarboh_qy(double carboh_qy) {
		this.carboh_qy = carboh_qy;
	}

	public double getProtein_qy() {
		return protein_qy;
	}

	public void setProtein_qy(double protein_qy) {
		this.protein_qy = protein_qy;
	}

	public double getFat_qy() {
		return fat_qy;
	}

	public void setFat_qy(double fat_qy) {
		this.fat_qy = fat_qy;
	}

	public double getCellu_qy() {
		return cellu_qy;
	}

	public void setCellu_qy(double cellu_qy) {
		this.cellu_qy = cellu_qy;
	}

	public double getCalcium_qy() {
		return calcium_qy;
	}

	public void setCalcium_qy(double calcium_qy) {
		this.calcium_qy = calcium_qy;
	}

	public double getPhosph_qy() {
		return phosph_qy;
	}

	public void setPhosph_qy(double phosph_qy) {
		this.phosph_qy = phosph_qy;
	}

	public double getFe_qy() {
		return fe_qy;
	}

	public void setFe_qy(double fe_qy) {
		this.fe_qy = fe_qy;
	}

	public double getNatrium_qy() {
		return natrium_qy;
	}

	public void setNatrium_qy(double natrium_qy) {
		this.natrium_qy = natrium_qy;
	}

	public double getPotassium_qy() {
		return potassium_qy;
	}

	public void setPotassium_qy(double potassium_qy) {
		this.potassium_qy = potassium_qy;
	}

	public double getVitamina_qy() {
		return vitamina_qy;
	}

	public void setVitamina_qy(double vitamina_qy) {
		this.vitamina_qy = vitamina_qy;
	}

	public double getThiamin_qy() {
		return thiamin_qy;
	}

	public void setThiamin_qy(double thiamin_qy) {
		this.thiamin_qy = thiamin_qy;
	}

	public double getRiboflamin_qy() {
		return riboflamin_qy;
	}

	public void setRiboflamin_qy(double riboflamin_qy) {
		this.riboflamin_qy = riboflamin_qy;
	}

	public double getNiacin_qy() {
		return niacin_qy;
	}

	public void setNiacin_qy(double niacin_qy) {
		this.niacin_qy = niacin_qy;
	}

	public double getVitaminc_qy() {
		return vitaminc_qy;
	}

	public void setVitaminc_qy(double vitaminc_qy) {
		this.vitaminc_qy = vitaminc_qy;
	}

	public String getMeal_pictr_file_nm() {
		return meal_pictr_file_nm;
	}

	public void setMeal_pictr_file_nm(String meal_pictr_file_nm) {
		this.meal_pictr_file_nm = meal_pictr_file_nm;
	}

	@Override
	public String toString() {
		return "DietVO [meal_clsf_nm=" + meal_clsf_nm + ", meal_nm=" + meal_nm + ", cook_mth_cont=" + cook_mth_cont
				+ ", matrl_nm=" + matrl_nm + ", calorie_qy=" + calorie_qy + ", carboh_qy=" + carboh_qy + ", protein_qy="
				+ protein_qy + ", fat_qy=" + fat_qy + ", cellu_qy=" + cellu_qy + ", calcium_qy=" + calcium_qy
				+ ", phosph_qy=" + phosph_qy + ", fe_qy=" + fe_qy + ", natrium_qy=" + natrium_qy + ", potassium_qy="
				+ potassium_qy + ", vitamina_qy=" + vitamina_qy + ", thiamin_qy=" + thiamin_qy + ", riboflamin_qy="
				+ riboflamin_qy + ", niacin_qy=" + niacin_qy + ", vitaminc_qy=" + vitaminc_qy + ", meal_pictr_file_nm="
				+ meal_pictr_file_nm + "]";
	}

	
}
