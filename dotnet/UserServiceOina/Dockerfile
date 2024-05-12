FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS base
WORKDIR /app
EXPOSE 8080
EXPOSE 8081

FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /src
COPY ["UserServiceOina.csproj", "./"]
RUN dotnet restore "UserServiceOina.csproj"
COPY . .
RUN dotnet build "UserServiceOina.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "UserServiceOina.csproj" -c Release -o /app/publish /p:UseAppHost=false

FROM base AS final
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "UserServiceOina.dll"]
