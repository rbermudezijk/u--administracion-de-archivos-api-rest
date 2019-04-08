package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="archivos_descripcion")
public class FileModel {
	
	/** Columns. */
	@Id
    @Column(name="id_archivo")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="nombre")
    private String name;
    @Column(name="extension")
    private String extension;
    @Column(name="tipo_mime")
    private String mimeType;
    @Column(name="contenido")
    private String content;
    
    
    /** Constructors, getters and setters. */
    
    public FileModel() {}

    public FileModel(int id, String name, String extension, String mimeType) {
		this.id = id;
		this.name = name;
		this.extension = extension;
		this.mimeType = mimeType;
	}
    
    public FileModel(int id, String name, String extension, String mimeType, String content) {
		this.id = id;
		this.name = name;
		this.extension = extension;
		this.mimeType = mimeType;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
    
}
