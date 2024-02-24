# Declara a imagem base com a versão do node do projeto
FROM node:20.10.0-alpine

# Define o diretorio padrao de dos comandos
WORKDIR /app

# Copia o package.json para o diretorio padrao
COPY package*.json ./

# Instala as dependências
RUN npm install

# Copia todos os arquivos para rodar a aplicação
COPY . .

# Expoe a porta usada pela aplicação
EXPOSE 5173

# Comando para iniciar o server usando o o host 0.0.0.0
CMD [ "npm", "run", "dev", "--", "--host", "0.0.0.0" ]

