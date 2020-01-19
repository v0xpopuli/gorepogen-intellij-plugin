func (${receiverName}) FindAll() ([]${entityNameWithPackage}, error) {
    var entities []${entityNameWithPackage}
    err := r.DB.Find(&entities).Error
    return entities, err
}

func (${receiverName}) FindById(id uint) (${entityNameWithPackage}, error) {
    var entity ${entityNameWithPackage}
    err := r.DB.First(&entity, id).Error
    return entity, err
}

func (${receiverName}) Save(entity ${entityNameWithPackage}) (${entityNameWithPackage}, error) {
    err := r.DB.Create(&entity).Error
    return entity, err
}

func (${receiverName}) Update(entity ${entityNameWithPackage}) error {
    return r.DB.UpdateColumns(&entity).Error
}

func (${receiverName}) Delete(entity ${entityNameWithPackage}) error {
    return r.DB.Delete(&entity).Error
}

func (${receiverName}) Count() (uint, error) {
    var count uint
    err := r.DB.Model(&${entityNameWithPackage}{}).Count(&count).Error
    return count, err
}
