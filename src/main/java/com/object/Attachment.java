//$Id$
package com.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Attachment {

	@Id
	@Column(name = "attachmentid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long attachmentid;	
	
	private String Attachmentpath;
	private long updatetime;
	private long userid;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getAttachmentid() {
		return attachmentid;
	}
	public void setAttachmentid(long attachmentid) {
		this.attachmentid = attachmentid;
	}
	public String getAttachmentpath() {
		return Attachmentpath;
	}
	public void setAttachmentpath(String attachmentpath) {
		Attachmentpath = attachmentpath;
	}
	public long getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	
	
}
