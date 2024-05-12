FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS base
WORKDIR /app
EXPOSE 8080
EXPOSE 8081

FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /src
COPY ["ToysService.csproj", "./"]
RUN dotnet restore "ToysService.csproj"
COPY . .
RUN dotnet build "ToysService.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "ToysService.csproj" -c Release -o /app/publish /p:UseAppHost=false

FROM base AS final
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "ToysService.dll"]
