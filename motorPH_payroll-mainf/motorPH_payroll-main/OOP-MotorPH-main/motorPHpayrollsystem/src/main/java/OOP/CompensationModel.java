package OOP;

public class CompensationModel {
    private double basicSalary;
    private double hourlyRate;
    private double riceSubsidy;
    private double phoneSubsidy;
    private double clothingAllowance;

    public CompensationModel(double basicSalary, double hourlyRate, 
                           double riceSubsidy, double phoneSubsidy, 
                           double clothingAllowance) {
        this.basicSalary = basicSalary;
        this.hourlyRate = hourlyRate;
        this.riceSubsidy = riceSubsidy;
        this.phoneSubsidy = phoneSubsidy;
        this.clothingAllowance = clothingAllowance;
    }

    //Getters and setters
    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getRiceSubsidy() {
        return riceSubsidy;
    }

    public void setRiceSubsidy(double riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }

    public double getPhoneSubsidy() {
        return phoneSubsidy;
    }

    public void setPhoneSubsidy(double phoneSubsidy) {
        this.phoneSubsidy = phoneSubsidy;
    }

    public double getClothingAllowance() {
        return clothingAllowance;
    }

    public void setClothingAllowance(double clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }

    public double calculateTotalAllowances() {
        return riceSubsidy + phoneSubsidy + clothingAllowance;
    }
}
