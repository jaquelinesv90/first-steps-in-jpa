package algaworksEbook;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CarPersistenceBLOB {
	
	public static void main(String[] args) throws IOException{
		//Lê bytes do arquivo da imagem
		Path path = FileSystems.getDefault()
				.getPath("images/index.jpg");
		byte[] photo= Files.readAllBytes(path);
		
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Vehicle vehicle = new Vehicle();
		vehicle.setManufacturer("Hyundai");
		vehicle.setModel("ix35");
		vehicle.setValue(new BigDecimal(100_000));
		vehicle.setFuelType(FuelType.BIOFUEL);
		vehicle.setDateRegister(new Date());
		vehicle.setPhoto(photo);
		
		manager.persist(vehicle);
		tx.commit();
		manager.detach(vehicle);
		
		Vehicle vehicle2 = manager.find(Vehicle.class, vehicle.getCode());
		if(vehicle2.getPhoto() != null) {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(
					vehicle2.getPhoto()));
			JOptionPane.showMessageDialog(null, new JLabel(
					new ImageIcon(img)));
		}else {
			System.out.print("Vehicle doesn't have photo");
		}
		
		manager.close();
		JpaUtil.close();
	}
}
