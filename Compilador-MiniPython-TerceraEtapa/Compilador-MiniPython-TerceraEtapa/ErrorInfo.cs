namespace MiniPython
{
    public class ErrorInfo
    {
        public int Line { get; set; }
        public int Column { get; set; }
        public string Message { get; set; }

        public override string ToString() => $"Línea {Line}, Columna {Column}: {Message}";
    }
}