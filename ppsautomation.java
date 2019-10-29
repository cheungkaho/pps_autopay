package ppsautomation;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.InetSocketAddress;

import java.net.Proxy;

import java.net.URL;

import java.net.URLConnection;

import java.util.concurrent .TimeUnit;


import javax.swing.JLabel;

import javax.swing.JPasswordField;

import javax.swing.JTextField;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JPanel;


import org.openqa.selenium.*;

import org.openqa.selenium.chrome.*;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ppsautomation extends JFrame implements ActionListener{


JButton loginButton = new JButton ("Login");

JLabel userNameLab = new JLabel ("User Name:");

JLabel pwdLab = new JLabel("Password: ");

JLabel captchaLab = new JLabel ("Captcha : ");

JLabel amtPerTxnLab= new JLabel("Amount per each transaction :");

JLabel loopLab = new JLabel("Loop : ");

static JTextField userNameInput = new JTextField (); 

static JPasswordField pwdInput = new JPasswordField();

static JTextField captchaInput = new JTextField ();

    static JTextField amtPerTxnInput = new JTextField ();

    static JTextField loopInput = new JTextField ();

JButton submitButton=new JButton ("Submit");

static Boolean inputted = false;


 public ppsautomation () {

     JFrame appFrame = new JFrame();

     appFrame.setTitle("PPS Automation");

     JPanel loginPanel= new JPanel();

     appFrame.setSize(500, 250);

GridBagLayout layout = new GridBagLayout();

loginPanel.setLayout(layout) ; 

GridBagConstraints gbc = new GridBagConstraints();

gbc.weightx=0.1;

gbc.fill= GridBagConstraints.HORIZONTAL;

gbc.gridx=0;

gbc.gridy=0;

gbc.insets = new Insets (5,5,5,5);

loginPanel.add(amtPerTxnLab,gbc);

 

gbc.weightx=0.9;

gbc.gridx=0;

gbc.gridy=1;

loginPanel.add(amtPerTxnInput,gbc);

 

gbc.weightx=0.1;

gbc.gridx=0;

gbc.gridy=1;

loginPanel.add(loopLab,gbc);

 

gbc.weightx=0.1;

gbc.gridx=1;

gbc.gridy=1;

loginPanel.add(loopInput,gbc);

 

gbc.weightx=1;

gbc.gridx=0;

gbc.gridy=4;

gbc.gridwidth=2;

loginPanel.add(submitButton,gbc);

 

submitButton.addActionListener(new ActionListener() {

@Override

public void actionPerformed(ActionEvent e) {

amtPerTxnInput.setEditable(false);

loopInput.setEditable(false);

inputted=true;

}

});

appFrame.add(loginPanel);

appFrame.setVisible(true);

}

    

 public static void main (String[] args) {

String merchantCode;

String billNumber;

System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.3");

System.setProperty("webdriver.chrome.driver", "C:/Users/tracy/Downloads/chromedriver_win32.78.0.3904.70/chromedriver.exe");

ChromeOptions options = new ChromeOptions();

options.setBinary("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");

WebDriver driver = new ChromeDriver(options);

driver.get("https://www.ppshk.com/pps/pps2/revamp2/template/pc/login_c.jsp");

new ppsautomation();

// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

WebDriverWait wait = new WebDriverWait(driver,60);

wait.until(new ExpectedCondition<Boolean>() {

public Boolean apply(WebDriver driver) {

return inputted!=false;

}

});

System.out.println("Amount detail: ");
System.out.println("amtPerTxnInput: "+amtPerTxnInput.getText());
System.out.println("loopInput: "+loopInput.getText());
System.out.println("End Amount detail");
wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("AMOUNT")));
System.out.println("start");
WebElement merchantElement=driver.findElement(By.xpath(""));
merchantCode=merchantElement.getText();
WebElement BillNumberElement=driver.findElement(By.xpath(""));
billNumber=BillNumberElement.getText();
WebElement submittTxnButton=driver.findElement(By.xpath(""));
System.out.println("merchantCode= "+merchantCode);
System.out.println("BillNumber= "+billNumber);
WebElement amt=driver.findElement(By.className("AMOUNT"));
amt.sendKeys(amtPerTxnInput.getText().toString());
}

@Override

public void actionPerformed(ActionEvent e) {
	if (e.getSource() == loginButton) {
		String data = userNameInput.getText();
		System.out.println(data);
		data = pwdInput.getText();
		System.out.println(data);
	}
}
public static String captchaImage() {
	String content;
	try {
		content=getText("https://www.ppshk.com/pps/pps2/revamp2/template/pc/login_c.jsp");
		System.out.println(content);
		System.out.println(content.indexOf("/pps/botdetectcaptcha?get=image&amp;c=exampleCaptchaTag&amp;t="));
		String captureImagePath="https:/www.ppshkcom"+content.substring(8010,8104);
		captureImagePath=captureImagePath.replaceAll("&amp;","&");
		return captureImagePath;
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

private static String getText(String url) throws Exception {
	URL website = new URL(url);
	URLConnection connection= website.openConnection();
	connection.setDoOutput(true);
	BufferedReader in = new BufferedReader( new InputStreamReader( connection.getInputStream()));
	StringBuilder response = new StringBuilder();
	String inputLine;
	while ((inputLine = in.readLine()) != null)
		response.append(inputLine);
	
	in.close();
	
	return response.toString();
}

}

