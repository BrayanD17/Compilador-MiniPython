using System;
using System.Collections.Generic;

public class SymbolTable
{
    private List<Dictionary<string, SymbolInfo>> scopes;
    private int nivelActual;

    public SymbolTable()
    {
        scopes = new List<Dictionary<string, SymbolInfo>>();
        nivelActual = -1;
        OpenScope();
    }

    public void OpenScope()
    {
        nivelActual++;
        if (nivelActual >= scopes.Count)
        {
            scopes.Add(new Dictionary<string, SymbolInfo>());
        }
    }

    public void CloseScope()
    {
        if (nivelActual >= 0)
        {
            scopes[nivelActual].Clear();
            nivelActual--;
        }
    }

    public void InsertarVariable(string name, string tipo, bool isConstant = false)
    {
        if (nivelActual >= 0 && nivelActual < scopes.Count)
        {
            var varInfo = new VariableInfo(name, tipo, isConstant, nivelActual);
            scopes[nivelActual][name] = varInfo;
        }
    }

    public void InsertarFuncion(string name, string tipo, List<string> parametros)
    {
        if (nivelActual >= 0 && nivelActual < scopes.Count)
        {
            var funcInfo = new MethodInfo(name, tipo, parametros, nivelActual);
            scopes[nivelActual][name] = funcInfo;
        }
    }

    public SymbolInfo Buscar(string name)
    {
        for (int i = nivelActual; i >= 0; i--)
        {
            if (scopes[i].TryGetValue(name, out var symbol))
            {
                return symbol;
            }
        }
        return null;
    }

    public SymbolInfo BuscarEnNivelActual(string name)
    {
        if (nivelActual >= 0 && nivelActual < scopes.Count)
        {
            scopes[nivelActual].TryGetValue(name, out var symbol);
            return symbol;
        }
        return null;
    }

    public void ImprimirTablaSimbolos(List<string> output)
    {
        output.Add("----- INICIO TABLA ------");
        for (int i = 0; i <= nivelActual; i++)
        {
            output.Add($"Nivel {i}:");
            foreach (var id in scopes[i].Values)
            {
                output.Add(id.GetInfo());
            }
        }
        output.Add("----- FIN TABLA ------");
    }
}

public abstract class SymbolInfo
{
    public string Name { get; }
    public string Type { get; }
    public int ScopeLevel { get; }

    protected SymbolInfo(string name, string type, int scopeLevel)
    {
        Name = name;
        Type = type;
        ScopeLevel = scopeLevel;
    }

    public abstract string GetInfo();
}

public class VariableInfo : SymbolInfo
{
    public bool IsConstant { get; }

    public VariableInfo(string name, string type, bool isConstant, int scopeLevel)
        : base(name, type, scopeLevel)
    {
        IsConstant = isConstant;
    }

    public override string GetInfo()
    {
        return $"Variable: {Name}, Tipo: {Type}, Nivel: {ScopeLevel}, Constante: {IsConstant}";
    }
}

public class MethodInfo : SymbolInfo
{
    public List<string> Params { get; }

    public MethodInfo(string name, string type, List<string> parametros, int scopeLevel)
        : base(name, type, scopeLevel)
    {
        Params = parametros ?? new List<string>();
    }

    public override string GetInfo()
    {
        return $"Función: {Name}, Tipo: {Type}, Nivel: {ScopeLevel}, Parámetros: {Params.Count}";
    }
}
