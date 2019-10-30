package project.implement;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PPSAutomation extends JFrame implements ActionListener{
	
    JButton loginButton = new JButton("Login");
    JLabel userNameLab = new JLabel("User Name:");
    JLabel pwdLab = new JLabel("Password: ");
    JLabel captchaLab = new JLabel("Captcha: ");
    JLabel amtPerTxnLab = new JLabel("Amount per each transaction: ");
    JLabel loopLab = new JLabel("Loop: ");
    static JTextField userNameInput = new JTextField();
    static JPasswordField pwdInput = new JPasswordField();
    static JTextField captchaInput = new JTextField();
    static JTextField amtPerTxnInput = new JTextField();
    static JTextField loopInput = new JTextField();
    JButton submitButton= new JButton("Submit");
    Image image = null;
    static Boolean inputted=false;
    
	public PPSAutomation() {
		
//        captchaImage();
        JFrame appFrame = new JFrame();
        appFrame.setTitle("PPS Login System");
        JPanel loginPanel = new JPanel();
        appFrame.setSize(500, 250);
        GridBagLayout layout = new GridBagLayout();
        loginPanel.setLayout(layout);  
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,5,5,5); 
        loginPanel.add(amtPerTxnLab, gbc);
        
        gbc.weightx = 0.9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(amtPerTxnInput, gbc);
        
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(loopLab, gbc);
        
        gbc.weightx = 0.9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(loopInput, gbc);
        
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.weightx = 0.1;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(5,5,5,5); 
//        loginPanel.add(userNameLab, gbc);
//        
//        gbc.weightx = 0.9;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        loginPanel.add(userNameInput, gbc);
//        
//        gbc.weightx = 0.1;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        loginPanel.add(pwdLab, gbc);
//        
//        gbc.weightx = 0.9;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        loginPanel.add(pwdInput, gbc);
//        
//        gbc.weightx = 0.1;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        loginPanel.add(captchaLab, gbc);
//        
//        gbc.weightx = 0.9;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 1;
//        gbc.gridy = 2;
//        loginPanel.add(captchaInput, gbc);
//        
//        System.out.println(captureImagePath);
//        try{
//        URL url = new URL(captureImagePath);
//        image = ImageIO.read(url);
//        ImageIcon imageicon=new ImageIcon(image);
//        JLabel lable = new JLabel(imageicon);
//        gbc.weightx = 0.9;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        gbc.gridwidth = 2; 
//        loginPanel.add(lable, gbc);
//        }catch(Exception e){
//        	e.printStackTrace();
//        }
        
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; 
        loginPanel.add(submitButton, gbc);
        
        submitButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 System.out.println("Submitted Amount Detail");
        	 amtPerTxnInput.setEditable(false);
        	 loopInput.setEditable(false);
             inputted=true;
         }
      });
        appFrame.add(loginPanel);
        appFrame.setVisible(true);  
    }
	
	


    public static void main(String[] args) {
     
        String merchantCode="";
        String billNumber="";
        int location=-1;
        int loop=1;
        PrintStream fileOut = null;
		try {
			Date date = new Date(); // this object contains the current date value
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//            System.out.println(formatter.format(date));
			fileOut = new PrintStream("D:/ppsResult_"+formatter.format(date)+".txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.setOut(fileOut);
        System.out.print("Start at "+ new Date());
		System.setProperty("https.proxyHost", "10.30.35.100");
        System.setProperty("https.proxyPort", "8080");
		System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
		System.setProperty("webdriver.chrome.driver", "D:/Users/tracyHTLam/Desktop/Tracy/Software/ungoogled-chromium-78.0.3904.70-1_windows/chromedriver.exe");
		ChromeOptions options = new ChromeOptions(); 
		options.setBinary("D:/Users/tracyHTLam/Desktop/Tracy/Software/ungoogled-chromium-78.0.3904.70-1_windows/chrome.exe");
		WebDriver driver=new ChromeDriver(options);
		try{	
//		driver.manage().window().maximize();
		driver.get("https://www.ppshk.com/pps/pps2/revamp2/template/pc/login_c.jsp");
//		WebElement PIN=driver.findElement(By.name("PIN"));
//        WebElement PMA= driver.findElement(By.name("PMA"));	
//        WebElement captchaCode= driver.findElement(By.name("captchaCode"));	
//        WebElement captchaImage = driver.findElement(By.id("exampleCaptchaTag_CaptchaImage"));
//        String src = captchaImage.getAttribute("src");
//        System.out.println(src);
        new PPSAutomation();
//        WebElement loginButton= driver.findElement(By.xpath("/html/body/table/tbody/tr[5]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[12]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td/a"));
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 80);
        wait.until(new ExpectedCondition<Boolean>() {

        public Boolean apply(WebDriver driver) {    
        	return inputted !=false;	
        }
        });
        System.out.println("Amount detail: ");
        System.out.println(amtPerTxnInput.getText());
        System.out.println(loopInput.getText());
        System.out.println("End Amount detail");
        while (loop<= Integer.parseInt(loopInput.getText())){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("MERCHANT_CODE")));
//        merchantCode= "18";
//        billNumber="4966040519417158";
        if (!merchantCode.isEmpty() && !billNumber.isEmpty() && location == -1){
            for (int i =3; i<=20;i++){
        	  String cMerchant = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr["+i+"]/td[2]")).getText();
        	  String cBillNumber = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr["+i+"]/td[4]")).getText();
        	   if (cMerchant!=null && cBillNumber!=null){
        		   if (cMerchant.equals(merchantCode) && cBillNumber.equals(billNumber)){
        			   location =i;
        			   System.out.println("location= "+location);
        			   break;
        		   }else
        			   continue;
        	   }else
        		   break;
        		
        	}
        }
        if (location != -1){
        	WebElement selectBillButtonElement= driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr["+location+"]/td[6]/a"));
        	System.out.println("click selectBillButtonElement");
        	wait.until(ExpectedConditions.elementToBeClickable(selectBillButtonElement));
        	selectBillButtonElement.click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("AMOUNT")));
        System.out.println("start");
        WebElement merchantElement= driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[2]"));
        merchantCode=merchantElement.getText();
        WebElement billNumberElement= driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]"));
        WebElement submittTxnButton= driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/table/tbody/tr[6]/td[2]/a[2]"));
        WebElement cancelTxnButton= driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/table/tbody/tr[6]/td[2]/a[1]"));
        billNumber=billNumberElement.getText();
        System.out.println("merchantCode= "+merchantCode);
        System.out.println("billNumber= "+billNumber);
        WebElement amt=driver.findElement(By.name("AMOUNT"));
        amt.sendKeys(amtPerTxnInput.getText().toString());
//        submittTxnButton.click();
        loop+=1;
        wait.until(ExpectedConditions.elementToBeClickable(cancelTxnButton));
        cancelTxnButton.click();
//        WebElement payNumberElement= driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]"));
//        System.out.println("payNumberElement: "+payNumberElement.getText().toString());
//        WebElement payAnotherBillButtonElement= driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr[8]/td/a[2]"));
        
//        System.out.println(userNameInput.getText());
//        System.out.println(pwdInput.getText());
//        System.out.println(captchaInput.getText());
//        PIN.sendKeys(userNameInput.getText());
//        PMA.sendKeys(pwdInput.getText());
//        captchaCode.sendKeys(captchaInput.getText());
//        loginButton.click();
//    	String captureImagePath= captchaImage();
        System.out.println("loop= "+loop);
        }
        System.out.println("End at "+ new Date());
     }catch (UnhandledAlertException f) {
     try {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert data: " + alertText);
        alert.accept();
    } catch (NoAlertPresentException e) {
        e.printStackTrace();
    }
}
    }


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
            String data = userNameInput.getText(); //perform your operation
            System.out.println(data);
            data = pwdInput.getText(); //perform your operation
            System.out.println(data);
        }
	}
	
	public static String captchaImage(){
		String content ;
		try {
			content=getText("https://www.ppshk.com/pps/pps2/revamp2/template/pc/login_c.jsp");
//			System.out.println(content);
			System.out.println(content.indexOf("/pps/botdetectcaptcha?get=image&amp;c=exampleCaptchaTag&amp;t="));
			String captureImagePath="https://www.ppshk.com"+content.substring(8010, 8104);
			captureImagePath=captureImagePath.replaceAll("&amp;", "&");
			return captureImagePath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static  String getText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.30.35.100", 8080));
//        HttpsURLConnection connection = (HttpsURLConnection)website.openConnection(proxy);
//        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            response.append(inputLine);

        in.close();

        return response.toString();
    }
}
