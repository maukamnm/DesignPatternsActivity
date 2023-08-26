//package beans;
//
//import java.util.ArrayList;
//
//import javax.xml.bind.annotation.*;
//
//@XmlRootElement(name="Response")
//@XmlAccessorType(XmlAccessType.FIELD)
//public class ResponseDataModel extends ResponseModel {
//	private Album data;
//	
//	public ResponseDataModel() {
//		super();
//	}
//		public Album getData() {
//		return data;
//	}
//
//	public void setData(Album data) {
//		this.data = data;
//	}
//	public ResponseDataModel(int status, String message, Album data) {
//		super(status, message);
//		this.data = data;
//		if(data != null) this.data.setTracks(new ArrayList<Track>());
//	}
//
//}