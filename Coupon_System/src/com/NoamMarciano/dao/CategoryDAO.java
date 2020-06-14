package com.NoamMarciano.dao;

import com.NoamMarciano.beans.Category;

public interface CategoryDAO {

	int getCategoryID(Category category);

	Category getCategoryName(int ID);

}
