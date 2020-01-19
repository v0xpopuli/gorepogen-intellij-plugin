func New${entityName}Repository(db *gorm.DB) ${repositoryName} {
    return &${structName}{DB: db}
}