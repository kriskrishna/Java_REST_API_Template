package boeing.app.template.webservice.core.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Category")
public class Category {

	private Integer id;
	private Category parent;
	private String label;
	private Set<Category> childrenList = new HashSet<Category>(0);
	private Set<Asset> assetList = new HashSet<Asset>(0);

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonIgnore
	@ManyToOne(
			fetch = FetchType.LAZY)
	@JoinColumn(
			name = "ParentId",
			nullable = true)
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	@Column(
			nullable = false)
	@NotEmpty
	@Length(
			max = 128)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@JsonIgnore
	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "parent")
	public Set<Category> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(Set<Category> childrenList) {
		this.childrenList = childrenList;
	}

	@JsonIgnore
	@ManyToMany(
			fetch = FetchType.LAZY)
	@JoinTable(
			name = "XrefCategoryAsset",
			joinColumns = { @JoinColumn(
					name = "CategoryId",
					nullable = false,
					updatable = false,
					foreignKey = @ForeignKey(
							name = "FK_XrefCategoryAsset_Category")) },
			inverseJoinColumns = { @JoinColumn(
					name = "AssetId",
					nullable = false,
					updatable = false,
					foreignKey = @ForeignKey(
							name = "FK_XrefCategoryAsset_Asset")) })
	public Set<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(Set<Asset> assetList) {
		this.assetList = assetList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetList == null) ? 0 : assetList.hashCode());
		result = prime * result + ((childrenList == null) ? 0 : childrenList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (assetList == null) {
			if (other.assetList != null)
				return false;
		} else if (!assetList.equals(other.assetList))
			return false;
		if (childrenList == null) {
			if (other.childrenList != null)
				return false;
		} else if (!childrenList.equals(other.childrenList))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}

}
