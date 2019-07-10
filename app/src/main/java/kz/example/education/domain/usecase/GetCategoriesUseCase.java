package kz.example.education.domain.usecase;

import kz.example.education.data.repository.GetCategoryRepository;
import kz.example.education.domain.interfaces.LoadedDataInterface;
import kz.example.education.domain.models.Category;

public class GetCategoriesUseCase implements LoadedDataInterface {

    GetCategoryRepository getCategoryRepository;

    public GetCategoriesUseCase(){
        getCategoryRepository = new GetCategoryRepository(this);
    }

    public void execute(){
        getCategoryRepository.getCategories();
    }

    public Category sendResults(Category data){
        System.out.println("HERE" + data);
        return data;
    }

    @Override
    public void data(Category data) {
        sendResults(data);
    }
}
