package utils;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Comparator;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Utility Class that offers different methods to handle both logic and visuals

 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public final class Auxiliar {
	private Auxiliar() {}

	public static Icon adjustImage(Dimension dimCmp, URL urlImg) {
		ImageIcon img = new ImageIcon(urlImg);

		return new ImageIcon(img.getImage().getScaledInstance(dimCmp.width, dimCmp.height, Image.SCALE_SMOOTH));
	}

	public static LocalDate convertToLocalDate(Calendar c) {
		return c!=null ? LocalDateTime.ofInstant(c.toInstant(), c.getTimeZone().toZoneId()).toLocalDate() : null;
	}
	
	public static Comparator<Integer> getCompInteger(){
		return new Comparator<Integer>() {

			@Override
			public int compare(Integer a, Integer b) {
				return Integer.compare(a, b);
			}
		};
	}
	
	public static Comparator<Double> getCompDouble(){
		return new Comparator<Double>() {

			@Override
			public int compare(Double a, Double b) {
				return Double.compare(a, b);
			}
		};
	}
	
}
