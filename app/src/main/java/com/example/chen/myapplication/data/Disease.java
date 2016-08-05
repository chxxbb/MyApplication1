package com.example.chen.myapplication.data;

public class Disease {
	
	private Integer id;
	
	private String name;
	
	private String symptom;
	
	private String hazard;
	
	private String cure;
	
	private String prevent;
	
	private Integer sectionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getHazard() {
		return hazard;
	}

	public void setHazard(String hazard) {
		this.hazard = hazard;
	}

	public String getCure() {
		return cure;
	}

	public void setCure(String cure) {
		this.cure = cure;
	}

	public String getPrevent() {
		return prevent;
	}

	public void setPrevent(String prevent) {
		this.prevent = prevent;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	@Override
	public String toString() {
		return "Disease [id=" + id + ", name=" + name + ", symptom=" + symptom + ", hazard=" + hazard + ", cure=" + cure
				+ ", prevent=" + prevent + ", sectionId=" + sectionId + "]";
	}
	
}
