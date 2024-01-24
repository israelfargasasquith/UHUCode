function [salida] = funcionEj5(t)

    m = length(t);
    salida = zeros(1,m);

    for i = 1:m;
        if t(i)<0
            salida(i) = 0;
        else if t(i) >30
                salida(i) = 0;
        else
            salida(i) = -5 * exp(1)^(0.2*t(i)) * cos(0.9*t(i) - pi/6) + 0.8*exp(1)^(-2*t(i)) + 5;

        end
    
        end
    end
end
