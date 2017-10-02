package web;
import java.util.ArrayList;
import java.util.List;

public class ImageURL {
	List <ImageObject> list;
	
	public ImageURL(){
		list = new ArrayList<ImageObject>();
	}
	
	public List<ImageObject> operation(){
		list.add(new ImageObject("16.jpg","./WebContent/static/16.jpg"));
		list.add(new ImageObject("25.jpg","./WebContent/static/25.jpg"));
		list.add(new ImageObject("27.jpg","./WebContent/static/27.jpg"));
		list.add(new ImageObject("31.jpg","./WebContent/static/31.jpg"));
		list.add(new ImageObject("35.jpg","./WebContent/static/35.jpg"));	
		
		return list;
	}
	
	
}
