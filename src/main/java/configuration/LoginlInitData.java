package configuration;

import util.MainInitData;



public class LoginlInitData extends MainInitData
{
    /**
     * Script Name   : <b>LoginlInitData</b>
     * Generated     : <b>September 23, 2012</b>
     * Description   : Functional Test Script
     *
     * @since  2012/09/25
     * @author Abu
     */
    protected static LoginlInitData loginData = null;


    public static String admin_login ="";
    public static String production_executive_login ="";
    public static String jtrilapp_server="";
    public static String factory_head_login="";
    public static String store_manager_login="";
    public static String factory_QC_manager_login="";

    public static String valedit_campaign_google="";
    public static String valadd_campaign_bing="";
    public static String valedit_campaign_bing="";
    public static String valadd_adgroup_google="";
    public static String valedit_adgroup_google="";
    public static String valadd_exactkeyword_google="";
    public static String valadd_phrasekeyword_google="";
    public static String valadd_broadkeyword_google="";

    public static String valedit_exactkeyword_google="";
    public static String valedit_phrasekeyword_google="";
    public static String valedit_broadkeyword_google="";

    public static String valadd_textads_google="";
    public static String valadd_mobile_google="";
    public static String valadd_mobcarriers_google="";
    public static String valadd_mob_markuplang_google="";
    public static String valadd_negkeyword_google="";
    public static String valadd_placement_google="";
    public static String valadd_mobile_image_google="";


    /**
     * Constructor to initialize location variables.
     *
     * @throws Exception
     */
    protected LoginlInitData() throws Exception
    {
        super();
        // initialize variables
        initLoginlVariables();
    }
    //single instance of LocationData object
    public static LoginlInitData getInstance() throws Exception
    {
        if(loginData == null)
            loginData = new LoginlInitData();

        return loginData;
    }

    public void initLoginlVariables () throws Exception
    {
        // Valencia test data load
        jtrilapp_server = getScriptValue("jtrilapp_server");
        production_executive_login = getScriptValue("production_executive_login");
        factory_head_login = getScriptValue("factory_head_login");
        store_manager_login = getScriptValue("store_manager_login");
        factory_QC_manager_login = getScriptValue("factory_QC_manager_login");
        admin_login = getScriptValue("admin_login");

        
    }
}

