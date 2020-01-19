type ${entityName}Repository interface {
    FindAll() ([]${entityNameWithPackage}, error)
    FindById(uint) (${entityNameWithPackage}, error)
    Save(main.User) (${entityNameWithPackage}, error)
    Update(${entityNameWithPackage}) error
    Delete(${entityNameWithPackage}) error
    Count() (uint, error)
}