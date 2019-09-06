package com.example.sti_agent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Michael on 6/11/2019.
 */

public class UserPreferences {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context _context;

    @SuppressLint("CommitPrefEdits")
    public UserPreferences(Context _context) {
        this._context = _context;
        sharedPreferences = _context.getSharedPreferences(Constant.USER_PREF, Constant.PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    //    FirstLaunch Preferences
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(Constant.IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(Constant.IS_FIRST_TIME_LAUNCH, true);
    }

//    User login preferences
    public void setUserLogged(boolean usLg) {
        editor.putBoolean(Constant.IS_USER_LOGGED, usLg);
        editor.apply();
    }

    public boolean isUserLogged() {
        return sharedPreferences.getBoolean(Constant.IS_USER_LOGGED, false);
    }

    
    //Temporal database
    public void setMotorPtype(String MotorPtype) {
        editor.putString(Constant.MOTOR_INSURED_PERSONAL_TYPE, MotorPtype);
        editor.apply();
    }

    public String getMotorPtype() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_PERSONAL_TYPE, "");
    }

//Agent Personal Details Prefs
    public void setAgentEmail(String email) {
        editor.putString(Constant.AGENT_EMAIL, email);
        editor.apply();
    }

    public String getAgentEmail() {
        return sharedPreferences.getString(Constant.AGENT_EMAIL, "");
    }

    public void setAgentUsername(String agentUsername) {
        editor.putString(Constant.AGENT_USERNAME, agentUsername);
        editor.apply();
    }

    public String getAgentUsername() {
        return sharedPreferences.getString(Constant.AGENT_USERNAME, "");
    }

    public void setAgentBio(String agentBio) {
        editor.putString(Constant.BIO, agentBio);
        editor.apply();
    }

    public String getAgentBio() {
        return sharedPreferences.getString(Constant.BIO, "");
    }

    public void setAgentProfileImg(String agentProfileImg) {
        editor.putString(Constant.AGENT_PROFILE_IMAGE_URL, agentProfileImg);
        editor.apply();
    }

    public String getAgentProfileImg() {
        return sharedPreferences.getString(Constant.AGENT_PROFILE_IMAGE_URL, "");
    }

    public void setAgentPhoneNUM(String agentPhoneNUM) {
        editor.putString(Constant.AGENT_PHONE_NUM, agentPhoneNUM);
        editor.apply();
    }

    public String getAgentPhoneNUM() {
        return sharedPreferences.getString(Constant.AGENT_PHONE_NUM, "");
    }

    public void setAgentPin(String agentPin) {
        editor.putString(Constant.PIN, agentPin);
        editor.apply();
    }

    public String getAgentPin() {
        return sharedPreferences.getString(Constant.PIN, "");
    }

    public void setAgentFirstName(String agentFirstName) {
        editor.putString(Constant.AGENT_FIRSTNAME, agentFirstName);
        editor.apply();
    }

    public String getAgentFirstName() {
        return sharedPreferences.getString(Constant.AGENT_FIRSTNAME, "");
    }

    public void setAgentLastName(String agentLastName) {
        editor.putString(Constant.AGENT_LASTNAME, agentLastName);
        editor.apply();
    }

    public String getAgentLastName() {
        return sharedPreferences.getString(Constant.AGENT_LASTNAME, "");
    }

    public void setWalletBalance(String walletBalance) {
        editor.putString(Constant.WALLET_BALANCE, walletBalance);
        editor.apply();
    }

    public String getWalletBalance() {
        return sharedPreferences.getString(Constant.WALLET_BALANCE, "");
    }

    public void setBank(String bank) {
        editor.putString(Constant.BANK, bank);
        editor.apply();
    }

    public String getBank() {
        return sharedPreferences.getString(Constant.BANK, "");
    }
    public void setAccountNumber(String accountNumber) {
        editor.putString(Constant.ACCOUNT_NUMBER, accountNumber);
        editor.apply();
    }

    public String getAccountNumber() {
        return sharedPreferences.getString(Constant.ACCOUNT_NUMBER, "");
    }
    public void setUserId(String userId) {
        editor.putString(Constant.User_ID, userId);
        editor.apply();
    }

    public String getUserId() {
        return sharedPreferences.getString(Constant.User_ID, "");
    }

    public void setAgentNumber(String agentNumber) {
        editor.putString(Constant.Agent_ID, agentNumber);
        editor.apply();
    }

    public String getAgentNumber() {
        return sharedPreferences.getString(Constant.Agent_ID, "");
    }

    public void setUserToken(String userToken) {
        editor.putString(Constant.User_Token, userToken);
        editor.apply();
    }

    public String getUserToken() {
        return sharedPreferences.getString(Constant.User_Token, "");
    }

    public void setAccountName(String accountName) {
        editor.putString(Constant.ACCOUNT_NAME, accountName);
        editor.apply();
    }

    public String getAccountName() {
        return sharedPreferences.getString(Constant.ACCOUNT_NAME, "");
    }


    public void setMotorIPrefix(String MotorIPrefix) {
        editor.putString(Constant.MOTOR_INSURED_PREFIX, MotorIPrefix);
        editor.apply();
    }

    public String getMotorIPrefix() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_PREFIX, "");
    }

    public void setMotorICompanyName(String CompanyName) {
        editor.putString(Constant.MOTOR_INSURED_COMPANYNAME, CompanyName);
        editor.apply();
    }

    public String getMotorICompanyName() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_COMPANYNAME, "");
    }

    public void setMotorITinNumber(String tinNumber) {
        editor.putString(Constant.MOTOR_INSURED_TIN_NUMBER, tinNumber);
        editor.apply();
    }

    public String getMotorITinNumber() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_TIN_NUMBER, "");
    }

    public void setMotorIOff_addr(String offAddr) {
        editor.putString(Constant.MOTOR_INSURED_OFFICE_ADDRESS, offAddr);
        editor.apply();
    }

    public String getMotorIOff_addr() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_OFFICE_ADDRESS, "");
    }

    public void setMotorIContPerson(String contPerson) {
        editor.putString(Constant.MOTOR_INSURED_CONTACT_PERSON, contPerson);
        editor.apply();
    }

    public String getMotorIContPerson() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_CONTACT_PERSON, "");
    }


    public void setMotorIFirstName(String firstName) {
        editor.putString(Constant.MOTOR_INSURED_FIRSTNAME, firstName);
        editor.apply();
    }

    public String getMotorIFirstName() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_FIRSTNAME, "");
    }

    public void setMotorILastName(String lastName) {
        editor.putString(Constant.MOTOR_INSURED_LASTNAME, lastName);
        editor.apply();
    }

    public String getMotorILastName() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_LASTNAME, "");
    }


    public void setMotorIGender(String gender) {
        editor.putString(Constant.MOTOR_INSURED_GENDER, gender);
        editor.apply();
    }

    public String getMotorIGender() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_GENDER, "");
    }

    public void setMotorIResAdrr(String resAdrr) {
        editor.putString(Constant.MOTOR_RESIDENT_ADDRESS, resAdrr);
        editor.apply();
    }

    public String getMotorIResAdrr() {
        return sharedPreferences.getString(Constant.MOTOR_RESIDENT_ADDRESS, "");
    }

    public void setMotorINextKin(String nextKin) {
        editor.putString(Constant.MOTOR_NEXT_OF_KIN, nextKin);
        editor.apply();
    }

    public String getMotorINextKin() {
        return sharedPreferences.getString(Constant.MOTOR_NEXT_OF_KIN, "");
    }

    public void setMotorIPhoneNum(String phoneNum) {
        editor.putString(Constant.MOTOR_INSURED_PHONE_NUM, phoneNum);
        editor.apply();
    }

    public String getMotorIPhoneNum() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_PHONE_NUM, "");
    }

    public void setMotorIEmail(String email) {
        editor.putString(Constant.MOTOR_INSURED_EMAIL, email);
        editor.apply();
    }

    public String getMotorIEmail() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_EMAIL, "");
    }

    public void setMotorIMailingAddr(String mailingAddr) {
        editor.putString(Constant.MOTOR_INSURED_MAILING_ADDR, mailingAddr);
        editor.apply();
    }

    public String getMotorIMailingAddr() {
        return sharedPreferences.getString(Constant.MOTOR_INSURED_MAILING_ADDR, "");
    }

    public void setMotorIPersonal_image(String personal_image) {
        editor.putString("Personal_image", personal_image);
        editor.apply();
    }

    public String getMotorIPersonal_image() {
        return sharedPreferences.getString("Personal_image", "");
    }

    public void setMotorStartDate(String startDate) {
        editor.putString(Constant.MOTOR_STARTDATE, startDate);
        editor.apply();
    }

    public String getMotorStartDate() {
        return sharedPreferences.getString(Constant.MOTOR_STARTDATE, "");
    }

    public void setMotorPolicyType(String polyType) {
        editor.putString(Constant.MOTOR_POLICY_TYPE, polyType);
        editor.apply();
    }

    public String getMotorPolicyType() {
        return sharedPreferences.getString(Constant.MOTOR_POLICY_TYPE, "");
    }

    public void setMotorPrivateType(String privateType) {
        editor.putString(Constant.MOTOR_PRIVATE_TYPE, privateType);
        editor.apply();
    }

    public String getMotorPrivateType() {
        return sharedPreferences.getString(Constant.MOTOR_PRIVATE_TYPE, "");
    }

    public void setMotorPEnhanceType(String pEnhanceType) {
        editor.putString(Constant.MOTOR_PENHANCE_TYPE, pEnhanceType);
        editor.apply();
    }

    public String getMotorPEnhanceType() {
        return sharedPreferences.getString(Constant.MOTOR_PENHANCE_TYPE, "");
    }

    public void setMotorCommercialType(String commercialType) {
        editor.putString(Constant.MOTOR_COMMERCIAL_TYPE, commercialType);
        editor.apply();
    }

    public String getMotorCommercialType() {
        return sharedPreferences.getString(Constant.MOTOR_COMMERCIAL_TYPE, "");
    }

    public void setMotorCycleType(String motorCycleType) {
        editor.putString(Constant.MOTOR_CYCLE_TYPE, motorCycleType);
        editor.apply();
    }

    public String getMotorCycleType() {
        return sharedPreferences.getString(Constant.MOTOR_CYCLE_TYPE, "");
    }

    public void setMotorVehicleMake(String vehicleMake) {
        editor.putString(Constant.MOTOR_VEHICLE_MAKER, vehicleMake);
        editor.apply();
    }

    public String getMotorVehicleMake() {
        return sharedPreferences.getString(Constant.MOTOR_VEHICLE_MAKER, "");
    }

    public void setMotorVehicleType(String vehicleType) {
        editor.putString(Constant.MOTOR_VEHICLE_TYPE, vehicleType);
        editor.apply();
    }

    public String getMotorVehicleType() {
        return sharedPreferences.getString(Constant.MOTOR_VEHICLE_TYPE, "");
    }

    public void setMotorVehicleBody(String motorVehicleBody) {
        editor.putString(Constant.MOTOR_VEHICLE_BODY_TYPE, motorVehicleBody);
        editor.apply();
    }

    public String getMotorVehicleBody() {
        return sharedPreferences.getString(Constant.MOTOR_VEHICLE_BODY_TYPE, "");
    }
    public void setMotorVehicleYear(String vehicleYear) {
        editor.putString(Constant.MOTOR_VEHICLE_YEAR, vehicleYear);
        editor.apply();
    }

    public String getMotorVehicleYear() {
        return sharedPreferences.getString(Constant.MOTOR_VEHICLE_YEAR, "");
    }

    public void setMotorVehicleRegNum(String motorVehicleRegNum) {
        editor.putString(Constant.MOTOR_VEHICLE_REG_NUM, motorVehicleRegNum);
        editor.apply();
    }

    public String getMotorVehicleRegNum() {
        return sharedPreferences.getString(Constant.MOTOR_VEHICLE_REG_NUM, "");
    }

    public void setMotorVehicleChasisNum(String chasisNum) {
        editor.putString(Constant.MOTOR_VEHICLE_CHASIS_NUM, chasisNum);
        editor.apply();
    }

    public String getMotorVehicleChasisNum() {
        return sharedPreferences.getString(Constant.MOTOR_VEHICLE_CHASIS_NUM, "");
    }
    public void setMotorVehicleEngNum(String engNum) {
        editor.putString(Constant.MOTOR_VEHICLE_ENG_NUM, engNum);
        editor.apply();
    }

    public String getMotorVehicleEngNum() {
        return sharedPreferences.getString(Constant.MOTOR_VEHICLE_ENG_NUM, "");
    }

    public void setMotorCycleValue(String motorCycleValue) {
        editor.putString(Constant.MOTOR_CYCLE_VALUE, motorCycleValue);
        editor.apply();
    }

    public String getMotorCycleValue() {
        return sharedPreferences.getString(Constant.MOTOR_CYCLE_VALUE, "");
    }

    public void setMotorVehicleValue(String vehicleValue) {
        editor.putString(Constant.MOTOR_VEHICLE_VALUE, vehicleValue);
        editor.apply();
    }

    public String getMotorVehicleValue() {
        return sharedPreferences.getString(Constant.MOTOR_VEHICLE_VALUE, "");
    }


    public void setTempQuotePrice(int quotePrice) {
        editor.putInt(Constant.MOTOR_QUOTE_PRICE, quotePrice);
        editor.apply();
    }

    public int getTempQuotePrice() {
        return sharedPreferences.getInt(Constant.MOTOR_QUOTE_PRICE, 0);
    }


    //Swiss datas

    public void setSwissIPrefix(String SwissIPrefix) {
        editor.putString(Constant.SWISS_INSURED_PREFIX, SwissIPrefix);
        editor.apply();
    }

    public String getSwissIPrefix() {
        return sharedPreferences.getString(Constant.SWISS_INSURED_PERSONAL_TYPE, "");
    }


    public void setSwissIFirstName(String firstName) {
        editor.putString(Constant.SWISS_INSURED_FIRSTNAME, firstName);
        editor.apply();
    }

    public String getSwissIFirstName() {
        return sharedPreferences.getString(Constant.SWISS_INSURED_FIRSTNAME, "");
    }

    public void setSwissILastName(String lastName) {
        editor.putString(Constant.SWISS_INSURED_LASTNAME, lastName);
        editor.apply();
    }

    public String getSwissILastName() {
        return sharedPreferences.getString(Constant.SWISS_INSURED_LASTNAME, "");
    }


    public void setSwissIGender(String gender) {
        editor.putString(Constant.SWISS_INSURED_GENDER, gender);
        editor.apply();
    }

    public String getSwissIGender() {
        return sharedPreferences.getString(Constant.SWISS_INSURED_GENDER, "");
    }

    public void setSwissIResAdrr(String resAdrr) {
        editor.putString(Constant.SWISS_RESIDENT_ADDRESS, resAdrr);
        editor.apply();
    }

    public String getSwissIResAdrr() {
        return sharedPreferences.getString(Constant.SWISS_RESIDENT_ADDRESS, "");
    }

    public void setSwissIMaritalStatus(String maritalStatus) {
        editor.putString(Constant.SWISS_MARITAL_STATUS, maritalStatus);
        editor.apply();
    }

    public String getSwissIMaritalStatus() {
        return sharedPreferences.getString(Constant.SWISS_MARITAL_STATUS, "");
    }

    public void setSwissINextKin(String nextKin) {
        editor.putString(Constant.SWISS_NEXT_OF_KIN, nextKin);
        editor.apply();
    }

    public String getSwissINextKin() {
        return sharedPreferences.getString(Constant.SWISS_NEXT_OF_KIN, "");
    }


    public void setSwissINextKinAddr(String nextKinAddr) {
        editor.putString(Constant.SWISS_NEXT_OF_KIN_ADDR, nextKinAddr);
        editor.apply();
    }

    public String getSwissINextKinAddr() {
        return sharedPreferences.getString(Constant.SWISS_NEXT_OF_KIN_ADDR, "");
    }

    public void setSwissINextKinPhoneNum(String phoneNum) {
        editor.putString(Constant.SWISS_NEXT_OF_KIN_PHONENO, phoneNum);
        editor.apply();
    }

    public String getSwissINextKinPhoneNum() {
        return sharedPreferences.getString(Constant.SWISS_NEXT_OF_KIN_PHONENO, "");
    }

    public void setSwissIDob(String dob) {
        editor.putString(Constant.SWISS_DATE_OF_BIRTH, dob);
        editor.apply();
    }

    public String getSwissIDob() {
        return sharedPreferences.getString(Constant.SWISS_DATE_OF_BIRTH, "");
    }

    public void setSwissIDisable(String disable) {
        editor.putString(Constant.SWISS_DISABILITY, disable);
        editor.apply();
    }

    public String getSwissIDisable() {
        return sharedPreferences.getString(Constant.SWISS_DISABILITY, "");
    }

    public void setSwissIBenefit(String benefit) {
        editor.putString(Constant.SWISS_BENEFIT_CAT, benefit);
        editor.apply();
    }

    public String getSwissIBenefit() {
        return sharedPreferences.getString(Constant.SWISS_BENEFIT_CAT, "");
    }



    public void setSwissIPhoneNum(String phoneNum) {
        editor.putString(Constant.SWISS_INSURED_PHONE_NUM, phoneNum);
        editor.apply();
    }

    public String getSwissIPhoneNum() {
        return sharedPreferences.getString(Constant.SWISS_INSURED_PHONE_NUM, "");
    }

    public void setSwissIPersonal_image(String personal_image) {
        editor.putString("Personal_image", personal_image);
        editor.apply();
    }

    public String getSwissIPersonal_image() {
        return sharedPreferences.getString("Personal_image", "");
    }


    //Swiss Additional Insured
    public void setSwissIAddFirstName(String firstName) {
        editor.putString(Constant.SWISS_AddINSURED_FIRSTNAME, firstName);
        editor.apply();
    }

    public String getSwissIAddFirstName() {
        return sharedPreferences.getString(Constant.SWISS_AddINSURED_FIRSTNAME, "");
    }

    public void setSwissIAddLastName(String lastName) {
        editor.putString(Constant.SWISS_AddINSURED_LASTNAME, lastName);
        editor.apply();
    }

    public String getSwissIAddLastName() {
        return sharedPreferences.getString(Constant.SWISS_AddINSURED_LASTNAME, "");
    }

    public void setSwissIAddDOB(String dob) {
        editor.putString(Constant.SWISS_AddDATE_OF_BIRTH, dob);
        editor.apply();
    }

    public String getSwissIAddDOB() {
        return sharedPreferences.getString(Constant.SWISS_AddDATE_OF_BIRTH, "");
    }

    public void setSwissIAddGender(String gender) {
        editor.putString(Constant.SWISS_AddINSURED_GENDER, gender);
        editor.apply();
    }

    public String getSwissIAddGender() {
        return sharedPreferences.getString(Constant.SWISS_AddINSURED_GENDER, "");
    }

    public void setSwissIAddPhoneNum(String phoneNum) {
        editor.putString(Constant.SWISS_AddINSURED_PHONE_NUM, phoneNum);
        editor.apply();
    }

    public String getSwissIAddPhoneNum() {
        return sharedPreferences.getString(Constant.SWISS_AddINSURED_PHONE_NUM, "");

    }


    public void setSwissIAddEmail(String email) {
        editor.putString(Constant.SWISS_AddINSURED_EMAIL, email);
        editor.apply();
    }

    public String getSwissIAddEmail() {
        return sharedPreferences.getString(Constant.SWISS_AddINSURED_EMAIL, "");
    }


    public void setSwissIAddDisability(String disability) {
        editor.putString(Constant.SWISS_AddDISABILITY, disability);
        editor.apply();
    }

    public String getSwissIAddDisability() {
        return sharedPreferences.getString(Constant.SWISS_AddDISABILITY, "");
    }

    public void setSwissIAddBenefitCat(String benefitCat) {
        editor.putString(Constant.SWISS_AddBENEFIT_CAT, benefitCat);
        editor.apply();
    }

    public String getSwissIAddBenefitCat() {
        return sharedPreferences.getString(Constant.SWISS_AddBENEFIT_CAT, "");
    }

    public void setSwissIAddMaritalStatus(String maritalStatus) {
        editor.putString(Constant.SWISS_AddMARITAL_STATUS, maritalStatus);
        editor.apply();
    }

    public String getSwissIAddMaritalStatus() {
        return sharedPreferences.getString(Constant.SWISS_AddMARITAL_STATUS, "");
    }
    

    public void setTempSwissQuotePrice(int quotePrice) {
        editor.putInt(Constant.SWISS_QUOTE_PRICE, quotePrice);
        editor.apply();
    }

    public int getTempSwissQuotePrice() {
        return sharedPreferences.getInt(Constant.SWISS_QUOTE_PRICE, 0);
    }
    
    
    //Marine Datas

    public void setMarinePtype(String MarinePtype) {
        editor.putString(Constant.MARINE_INSURED_PERSONAL_TYPE, MarinePtype);
        editor.apply();
    }

    public String getMarinePtype() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_PERSONAL_TYPE, "");
    }

    public void setMarineIPrefix(String MarineIPrefix) {
        editor.putString(Constant.MARINE_INSURED_PREFIX, MarineIPrefix);
        editor.apply();
    }

    public String getMarineIPrefix() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_PREFIX, "");
    }

    public void setMarineICompanyName(String CompanyName) {
        editor.putString(Constant.MARINE_INSURED_COMPANYNAME, CompanyName);
        editor.apply();
    }

    public String getMarineICompanyName() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_COMPANYNAME, "");
    }

    public void setMarineITinNumber(String tinNumber) {
        editor.putString(Constant.MARINE_INSURED_TIN_NUMBER, tinNumber);
        editor.apply();
    }

    public String getMarineITinNumber() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_TIN_NUMBER, "");
    }

    public void setMarineIOff_addr(String offAddr) {
        editor.putString(Constant.MARINE_INSURED_OFFICE_ADDRESS, offAddr);
        editor.apply();
    }

    public String getMarineIOff_addr() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_OFFICE_ADDRESS, "");
    }

    public void setMarineITrade(String trade) {
        editor.putString(Constant.MARINE_INSURED_TRADE, trade);
        editor.apply();
    }

    public String getMarineITrade() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_TRADE, "");
    }

    public void setMarineIContPerson(String contPerson) {
        editor.putString(Constant.MARINE_INSURED_CONTACT_PERSON, contPerson);
        editor.apply();
    }

    public String getMarineIContPerson() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_CONTACT_PERSON, "");
    }


    public void setMarineIFirstName(String firstName) {
        editor.putString(Constant.MARINE_INSURED_FIRSTNAME, firstName);
        editor.apply();
    }

    public String getMarineIFirstName() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_FIRSTNAME, "");
    }

    public void setMarineILastName(String lastName) {
        editor.putString(Constant.MARINE_INSURED_LASTNAME, lastName);
        editor.apply();
    }

    public String getMarineILastName() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_LASTNAME, "");
    }


    public void setMarineIGender(String gender) {
        editor.putString(Constant.MARINE_INSURED_GENDER, gender);
        editor.apply();
    }

    public String getMarineIGender() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_GENDER, "");
    }



    public void setMarineIMaritalStatus(String maritalStatus) {
        editor.putString(Constant.MARINE_INSURED_MARITAL_STATUS, maritalStatus);
        editor.apply();
    }

    public String getMarineIMaritalStatus() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_MARITAL_STATUS, "");
    }


    public void setMarineIResAdrr(String resAdrr) {
        editor.putString(Constant.MARINE_RESIDENT_ADDRESS, resAdrr);
        editor.apply();
    }

    public String getMarineIResAdrr() {
        return sharedPreferences.getString(Constant.MARINE_RESIDENT_ADDRESS, "");
    }

    public void setMarineIPhoneNum(String phoneNum) {
        editor.putString(Constant.MARINE_INSURED_PHONE_NUM, phoneNum);
        editor.apply();
    }

    public String getMarineIPhoneNum() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_PHONE_NUM, "");
    }

    public void setMarineIEmail(String email) {
        editor.putString(Constant.MARINE_INSURED_EMAIL, email);
        editor.apply();
    }

    public String getMarineIEmail() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_EMAIL, "");
    }

    public void setMarineIMailingAddr(String mailingAddr) {
        editor.putString(Constant.MARINE_INSURED_MAILING_ADDR, mailingAddr);
        editor.apply();
    }

    public String getMarineIMailingAddr() {
        return sharedPreferences.getString(Constant.MARINE_INSURED_MAILING_ADDR, "");
    }


    //Marine Cargo Detail
    public void setMarineIProfInvNO(String profInvNO) {
        editor.putString(Constant.MARINE_PROF_INV_NO, profInvNO);
        editor.apply();
    }

    public String getMarineIProfInvNO() {
        return sharedPreferences.getString(Constant.MARINE_PROF_INV_NO, "");
    }

    public void setMarineIDateProfInv(String dateProfInv) {
        editor.putString(Constant.MARINE_DATE_PROF_INV, dateProfInv);
        editor.apply();
    }

    public String getMarineIDateProfInv() {
        return sharedPreferences.getString(Constant.MARINE_DATE_PROF_INV, "");
    }

    public void setMarineIDescOfGoods(String descOfGoods) {
        editor.putString(Constant.MARINE_DESC_GOODS, descOfGoods);
        editor.apply();
    }

    public String getMarineIDescOfGoods() {
        return sharedPreferences.getString(Constant.MARINE_DESC_GOODS, "");
    }

    public void setMarineIIntetrest(String intetrest) {
        editor.putString(Constant.MARINE_INTEREST, intetrest);
        editor.apply();
    }

    public String getMarineIIntetrest() {
        return sharedPreferences.getString(Constant.MARINE_INTEREST, "");
    }

    public void setMarineIQuantity(String quantity) {
        editor.putString(Constant.MARINE_QUANTITY, quantity);
        editor.apply();
    }

    public String getMarineIQuantity() {
        return sharedPreferences.getString(Constant.MARINE_QUANTITY, "");
    }

    public void setMarineICurrency(String currency) {
        editor.putString(Constant.MARINE_CURRENCY, currency);
        editor.apply();
    }

    public String getMarineICurrency() {
        return sharedPreferences.getString(Constant.MARINE_CURRENCY, "");
    }

    public void setMarineITotalAmount(String totalAmount) {
        editor.putString(Constant.MARINE_TOTAL_AMOUNT, totalAmount);
        editor.apply();
    }

    public String getMarineITotalAmount() {
        return sharedPreferences.getString(Constant.MARINE_TOTAL_AMOUNT, "");
    }

    public void setMarineINairaConvert(String nairaConvert) {
        editor.putString(Constant.MARINE_NAIRA_CONVERT_RATE, nairaConvert);
        editor.apply();
    }

    public String getMarineINairaConvert() {
        return sharedPreferences.getString(Constant.MARINE_NAIRA_CONVERT_RATE, "");
    }

    public void setMarineIPortOfLoading(String portOfLoading) {
        editor.putString(Constant.MARINE_PORT_OFLOADING, portOfLoading);
        editor.apply();
    }

    public String getMarineIPortOfLoading() {
        return sharedPreferences.getString(Constant.MARINE_PORT_OFLOADING, "");
    }

    public void setMarineIPortOfDischarge(String portOfLoading) {
        editor.putString(Constant.MARINE_PORT_OFDSICHARGE, portOfLoading);
        editor.apply();
    }

    public String getMarineIPortOfDischarge() {
        return sharedPreferences.getString(Constant.MARINE_PORT_OFDSICHARGE, "");
    }

    public void setMarineIProfImage(String profImage) {
        editor.putString("Prof_image", profImage);
        editor.apply();
    }

    public String getMarineIProfImage() {
        return sharedPreferences.getString("Prof_image", "");
    }

    public void setMarineIModeOfConvey(String modeOfConvey) {
        editor.putString(Constant.MARINE_MODE_OFCONVEY, modeOfConvey);
        editor.apply();
    }

    public String getMarineIModeOfConvey() {
        return sharedPreferences.getString(Constant.MARINE_MODE_OFCONVEY, "");
    }


    public void setTempMarineQuotePrice(int quotePrice) {
        editor.putInt(Constant.MARINE_QUOTE_PRICE, quotePrice);
        editor.apply();
    }

    public int getTempMarineQuotePrice() {
        return sharedPreferences.getInt(Constant.MARINE_QUOTE_PRICE, 0);
    }

    //All Risk Datas

    public void setAllRiskPtype(String AllRiskPtype) {
        editor.putString(Constant.ALLRISK_INSURED_PERSONAL_TYPE, AllRiskPtype);
        editor.apply();
    }

    public String getAllRiskPtype() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_PERSONAL_TYPE, "");
    }

    public void setAllRiskIPrefix(String AllRiskIPrefix) {
        editor.putString(Constant.ALLRISK_INSURED_PREFIX, AllRiskIPrefix);
        editor.apply();
    }

    public String getAllRiskIPrefix() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_PREFIX, "");
    }

    public void setAllRiskICompanyName(String CompanyName) {
        editor.putString(Constant.ALLRISK_INSURED_COMPANYNAME, CompanyName);
        editor.apply();
    }

    public String getAllRiskICompanyName() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_COMPANYNAME, "");
    }

    public void setAllRiskITinNumber(String tinNumber) {
        editor.putString(Constant.ALLRISK_INSURED_TIN_NUMBER, tinNumber);
        editor.apply();
    }

    public String getAllRiskITinNumber() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_TIN_NUMBER, "");
    }

    public void setAllRiskIOff_addr(String offAddr) {
        editor.putString(Constant.ALLRISK_INSURED_OFFICE_ADDRESS, offAddr);
        editor.apply();
    }

    public String getAllRiskIOff_addr() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_OFFICE_ADDRESS, "");
    }

    public void setAllRiskIContPerson(String contPerson) {
        editor.putString(Constant.ALLRISK_INSURED_CONTACT_PERSON, contPerson);
        editor.apply();
    }

    public String getAllRiskIContPerson() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_CONTACT_PERSON, "");
    }


    public void setAllRiskIFirstName(String firstName) {
        editor.putString(Constant.ALLRISK_INSURED_FIRSTNAME, firstName);
        editor.apply();
    }

    public String getAllRiskIFirstName() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_FIRSTNAME, "");
    }

    public void setAllRiskILastName(String lastName) {
        editor.putString(Constant.ALLRISK_INSURED_LASTNAME, lastName);
        editor.apply();
    }

    public String getAllRiskILastName() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_LASTNAME, "");
    }


    public void setAllRiskIGender(String gender) {
        editor.putString(Constant.ALLRISK_INSURED_GENDER, gender);
        editor.apply();
    }

    public String getAllRiskIGender() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_GENDER, "");
    }

    public void setAllRiskIResAdrr(String resAdrr) {
        editor.putString(Constant.ALLRISK_RESIDENT_ADDRESS, resAdrr);
        editor.apply();
    }

    public String getAllRiskIResAdrr() {
        return sharedPreferences.getString(Constant.ALLRISK_RESIDENT_ADDRESS, "");
    }

    public void setAllRiskINextKin(String nextKin) {
        editor.putString(Constant.ALLRISK_NEXT_OF_KIN, nextKin);
        editor.apply();
    }

    public String getAllRiskINextKin() {
        return sharedPreferences.getString(Constant.ALLRISK_NEXT_OF_KIN, "");
    }

    public void setAllRiskIPhoneNum(String phoneNum) {
        editor.putString(Constant.ALLRISK_INSURED_PHONE_NUM, phoneNum);
        editor.apply();
    }

    public String getAllRiskIPhoneNum() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_PHONE_NUM, "");
    }

    public void setAllRiskIEmail(String email) {
        editor.putString(Constant.ALLRISK_INSURED_EMAIL, email);
        editor.apply();
    }

    public String getAllRiskIEmail() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_EMAIL, "");
    }

    public void setAllRiskIMailingAddr(String mailingAddr) {
        editor.putString(Constant.ALLRISK_INSURED_MAILING_ADDR, mailingAddr);
        editor.apply();
    }

    public String getAllRiskIMailingAddr() {
        return sharedPreferences.getString(Constant.ALLRISK_INSURED_MAILING_ADDR, "");
    }


    public void setAllRiskItemType(String itemType) {
        editor.putString(Constant.ALLRISK_ITEM_TYPE, itemType);
        editor.apply();
    }

    public String getAllRiskItemType() {
        return sharedPreferences.getString(Constant.ALLRISK_ITEM_TYPE, "");
    }

    public void setAllRiskItemDesc(String desc) {
        editor.putString(Constant.ALLRISK_DESC_ITEM, desc);
        editor.apply();
    }

    public String getAllRiskItemDesc() {
        return sharedPreferences.getString(Constant.ALLRISK_DESC_ITEM, "");
    }

    public void setAllRiskStartDate(String startDate) {
        editor.putString(Constant.ALLRISK_START_DATE, startDate);
        editor.apply();
    }

    public String getAllRiskStartDate() {
        return sharedPreferences.getString(Constant.ALLRISK_START_DATE, "");
    }

    public void setAllRiskSerialNo(String serialNo) {
        editor.putString(Constant.ALLRISK_SERIAL_NO, serialNo);
        editor.apply();
    }

    public String getAllRiskSerialNo() {
        return sharedPreferences.getString(Constant.ALLRISK_SERIAL_NO, "");
    }

    public void setAllRiskPersonalImage(String personalImage) {
        editor.putString("Personal_image", personalImage);
        editor.apply();
    }

    public String getAllRiskPersonalImage() {
        return sharedPreferences.getString("Personal_image", "");
    }


    public void setAllRiskItemValue(String itemValue) {
        editor.putString(Constant.ALLRISK_ITEM_VALUE, itemValue);
        editor.apply();
    }

    public String getAllRiskItemValue() {
        return sharedPreferences.getString(Constant.ALLRISK_ITEM_VALUE, "");
    }

    
    public void setTempAllRiskQuotePrice(int quotePrice) {
        editor.putInt(Constant.ALLRISK_QUOTE_PRICE, quotePrice);
        editor.apply();
    }

    public int getTempAllRiskQuotePrice() {
        return sharedPreferences.getInt(Constant.ALLRISK_QUOTE_PRICE, 0);
    }

    //Etic datas

    public void setEticIPrefix(String EticIPrefix) {
        editor.putString(Constant.ETIC_INSURED_PREFIX, EticIPrefix);
        editor.apply();
    }

    public String getEticIPrefix() {
        return sharedPreferences.getString(Constant.ETIC_INSURED_PREFIX, "");
    }


    public void setEticIFirstName(String firstName) {
        editor.putString(Constant.ETIC_INSURED_FIRSTNAME, firstName);
        editor.apply();
    }

    public String getEticIFirstName() {
        return sharedPreferences.getString(Constant.ETIC_INSURED_FIRSTNAME, "");
    }

    public void setEticILastName(String lastName) {
        editor.putString(Constant.ETIC_INSURED_LASTNAME, lastName);
        editor.apply();
    }

    public String getEticILastName() {
        return sharedPreferences.getString(Constant.ETIC_INSURED_LASTNAME, "");
    }


    public void setEticIGender(String gender) {
        editor.putString(Constant.ETIC_INSURED_GENDER, gender);
        editor.apply();
    }

    public String getEticIGender() {
        return sharedPreferences.getString(Constant.ETIC_INSURED_GENDER, "");
    }

    public void setEticIResAdrr(String resAdrr) {
        editor.putString(Constant.ETIC_RESIDENT_ADDRESS, resAdrr);
        editor.apply();
    }

    public String getEticIResAdrr() {
        return sharedPreferences.getString(Constant.ETIC_RESIDENT_ADDRESS, "");
    }

    public void setEticINextKin(String nextKin) {
        editor.putString(Constant.ETIC_NEXT_OF_KIN, nextKin);
        editor.apply();
    }

    public String getEticINextKin() {
        return sharedPreferences.getString(Constant.ETIC_NEXT_OF_KIN, "");
    }

    public void setEticIPhoneNum(String phoneNum) {
        editor.putString(Constant.ETIC_INSURED_PHONE_NUM, phoneNum);
        editor.apply();
    }

    public String getEticIPhoneNum() {
        return sharedPreferences.getString(Constant.ETIC_INSURED_PHONE_NUM, "");
    }

    public void setEticIPersonalImage(String personalImage) {
        editor.putString("Personal_Image", personalImage);
        editor.apply();
    }

    public String getEticIPersonalImage() {
        return sharedPreferences.getString("Personal_Image", "");
    }

    public void setEticIEmail(String email) {
        editor.putString(Constant.ETIC_INSURED_EMAIL, email);
        editor.apply();
    }

    public String getEticIEmail() {
        return sharedPreferences.getString(Constant.ETIC_INSURED_EMAIL, "");
    }

    public void setEticIMailingAddr(String mailingAddr) {
        editor.putString(Constant.ETIC_INSURED_MAILING_ADDR, mailingAddr);
        editor.apply();
    }

    public String getEticIMailingAddr() {
        return sharedPreferences.getString(Constant.ETIC_INSURED_MAILING_ADDR, "");
    }


    public void setEticITripDuration(String tripDuration) {
        editor.putString(Constant.ETIC_TRIP_DURATION, tripDuration);
        editor.apply();
    }

    public String getEticITripDuration() {
        return sharedPreferences.getString(Constant.ETIC_TRIP_DURATION, "");
    }

    public void setEticStartDate(String startDate) {
        editor.putString(Constant.ETIC_STARTDATE, startDate);
        editor.apply();
    }

    public String getEticStartDate() {
        return sharedPreferences.getString(Constant.ETIC_STARTDATE, "");
    }

    public void setEticITravelMode(String travelMode) {
        editor.putString(Constant.ETIC_TRAVEL_MODE, travelMode);
        editor.apply();
    }

    public String getEticITravelMode() {
        return sharedPreferences.getString(Constant.ETIC_TRAVEL_MODE, "");
    }

    public void setEticIDisability(String disability) {
        editor.putString(Constant.ETIC_DISABILITY, disability);
        editor.apply();
    }

    public String getEticIDisability() {
        return sharedPreferences.getString(Constant.ETIC_DISABILITY, "");
    }


    public void setEticIDisabilityDetail(String disabilityDetail) {
        editor.putString(Constant.ETIC_DISABILITY_DETAIL, disabilityDetail);
        editor.apply();
    }

    public String getEticIDisabilityDetail() {
        return sharedPreferences.getString(Constant.ETIC_DISABILITY_DETAIL, "");
    }
    public void setEticIDeparturePlc(String departurePlc) {
        editor.putString(Constant.ETIC_DEPARTURE_PL, departurePlc);
        editor.apply();
    }

    public String getEticIDeparturePlc() {
        return sharedPreferences.getString(Constant.ETIC_DEPARTURE_PL, "");
    }

    public void setEticIArrivalPlc(String arrivalPlc) {
        editor.putString(Constant.ETIC_ARRIVAL_PL, arrivalPlc);
        editor.apply();
    }

    public String getEticIArrivalPlc() {
        return sharedPreferences.getString(Constant.ETIC_ARRIVAL_PL, "");
    }

    public void setEticICountryOfVisit(String countryOfVisit) {
        editor.putString(Constant.ETIC_COUNTRY_OFVISIT, countryOfVisit);
        editor.apply();
    }

    public String getEticICountryOfVisit() {
        return sharedPreferences.getString(Constant.ETIC_COUNTRY_OFVISIT, "");
    }




    public void setTempEticQuotePrice(int quotePrice) {
        editor.putInt(Constant.ETIC_QUOTE_PRICE, quotePrice);
        editor.apply();
    }

    public int getTempEticQuotePrice() {
        return sharedPreferences.getInt(Constant.ETIC_QUOTE_PRICE, 0);
    }

    //Other Insured datas

    public void setOtherPtype(String OtherPtype) {
        editor.putString(Constant.OTHER_INSURED_PERSONAL_TYPE, OtherPtype);
        editor.apply();
    }

    public String getOtherPtype() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_PERSONAL_TYPE, "");
    }

    public void setOtherIPrefix(String OtherIPrefix) {
        editor.putString(Constant.OTHER_INSURED_PREFIX, OtherIPrefix);
        editor.apply();
    }

    public String getOtherIPrefix() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_PREFIX, "");
    }

    public void setOtherICompanyName(String CompanyName) {
        editor.putString(Constant.OTHER_INSURED_COMPANYNAME, CompanyName);
        editor.apply();
    }

    public String getOtherICompanyName() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_COMPANYNAME, "");
    }

    public void setOtherITinNumber(String tinNumber) {
        editor.putString(Constant.OTHER_INSURED_TIN_NUMBER, tinNumber);
        editor.apply();
    }

    public String getOtherITinNumber() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_TIN_NUMBER, "");
    }

    public void setOtherIOff_addr(String offAddr) {
        editor.putString(Constant.OTHER_INSURED_OFFICE_ADDRESS, offAddr);
        editor.apply();
    }

    public String getOtherIOff_addr() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_OFFICE_ADDRESS, "");
    }

    public void setOtherIContPerson(String contPerson) {
        editor.putString(Constant.OTHER_INSURED_CONTACT_PERSON, contPerson);
        editor.apply();
    }

    public String getOtherIContPerson() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_CONTACT_PERSON, "");
    }


    public void setOtherIFirstName(String firstName) {
        editor.putString(Constant.OTHER_INSURED_FIRSTNAME, firstName);
        editor.apply();
    }

    public String getOtherIFirstName() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_FIRSTNAME, "");
    }

    public void setOtherILastName(String lastName) {
        editor.putString(Constant.OTHER_INSURED_LASTNAME, lastName);
        editor.apply();
    }

    public String getOtherILastName() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_LASTNAME, "");
    }


    public void setOtherIGender(String gender) {
        editor.putString(Constant.OTHER_INSURED_GENDER, gender);
        editor.apply();
    }

    public String getOtherIGender() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_GENDER, "");
    }

    public void setOtherIResAdrr(String resAdrr) {
        editor.putString(Constant.OTHER_RESIDENT_ADDRESS, resAdrr);
        editor.apply();
    }

    public String getOtherIResAdrr() {
        return sharedPreferences.getString(Constant.OTHER_RESIDENT_ADDRESS, "");
    }

    public void setOtherINextKin(String nextKin) {
        editor.putString(Constant.OTHER_NEXT_OF_KIN, nextKin);
        editor.apply();
    }

    public String getOtherINextKin() {
        return sharedPreferences.getString(Constant.OTHER_NEXT_OF_KIN, "");
    }

    public void setOtherIPhoneNum(String phoneNum) {
        editor.putString(Constant.OTHER_INSURED_PHONE_NUM, phoneNum);
        editor.apply();
    }

    public String getOtherIPhoneNum() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_PHONE_NUM, "");
    }

    public void setOtherIEmail(String email) {
        editor.putString(Constant.OTHER_INSURED_EMAIL, email);
        editor.apply();
    }

    public String getOtherIEmail() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_EMAIL, "");
    }

    public void setOtherIMailingAddr(String mailingAddr) {
        editor.putString(Constant.OTHER_INSURED_MAILING_ADDR, mailingAddr);
        editor.apply();
    }

    public String getOtherIMailingAddr() {
        return sharedPreferences.getString(Constant.OTHER_INSURED_MAILING_ADDR, "");
    }

    public void setOtherIPersonal_image(String personal_image) {
        editor.putString("Personal_image", personal_image);
        editor.apply();
    }

    public String getOtherIPersonal_image() {
        return sharedPreferences.getString("Personal_image", "");
    }

    public void setOtherProductType(String productType) {
        editor.putString(Constant.OTHER_PRODUCT_TYPE, productType);
        editor.apply();
    }

    public String getOtherProductType() {
        return sharedPreferences.getString(Constant.OTHER_PRODUCT_TYPE, "");
    }

    public void setOtherDescItem(String descItem) {
        editor.putString(Constant.OTHER_DESC_ITEM, descItem);
        editor.apply();
    }

    public String getOtherDescItem() {
        return sharedPreferences.getString(Constant.OTHER_DESC_ITEM, "");
    }

    public void setTempOtherQuotePrice(int quotePrice) {
        editor.putInt(Constant.OTHER_QUOTE_PRICE, quotePrice);
        editor.apply();
    }

    public int getTempOtherQuotePrice() {
        return sharedPreferences.getInt(Constant.OTHER_QUOTE_PRICE, 0);
    }




















}
